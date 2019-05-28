package com.xaehu.testview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xaehu.testview.bean.KugouDetail;
import com.xaehu.testview.bean.KugouSearch;
import com.xaehu.testview.util.RequestInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xaehu
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText editText;
    private ListView listView;
    private List<KugouSearch.DataBean.InfoBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    public void click(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mobilecdn.kugou.com/api/v3/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Map<String,Object> map = new HashMap(3);
        map.put("keyword",editText.getText().toString());
        map.put("page",1);
        map.put("pagesize",20);
        requestInterface.searchKugou(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KugouSearch>() {

                    @Override
                    public void onError(Throwable e) {
                        Log.e("myout", "onFailure: "+e.getMessage());
                        Toast.makeText(MainActivity.this, "连接失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KugouSearch kugouSearch) {
                        if(kugouSearch.getData()!=null&&kugouSearch.getData().getInfo()!=null){
                            list = kugouSearch.getData().getInfo();
                            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,list);
                            listView.setAdapter(adapter);
                        }else{
                            Toast.makeText(MainActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        //http://m.kugou.com/app/i/getSongInfo.php?cmd=playInfo&hash=CB7EE97F4CC11C4EA7A1FA4B516A5D97
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://m.kugou.com/app/i/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        requestInterface.getDetail(list.get(position).getHash())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KugouDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KugouDetail kugouDetail) {
                        if(kugouDetail.getErrcode() == 0){
                            View detailView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_detail,null);
                            ((TextView)detailView.findViewById(R.id.song)).setText(kugouDetail.getSongName());
                            ((TextView)detailView.findViewById(R.id.singer)).setText(kugouDetail.getSingerName());
                            ((TextView)detailView.findViewById(R.id.filesize)).setText(String.valueOf(kugouDetail.getFileSize()));
                            ((TextView)detailView.findViewById(R.id.time)).setText(String.valueOf(kugouDetail.getTimeLength()));
                            ((TextView)detailView.findViewById(R.id.hash)).setText(kugouDetail.getHash());
                            ((TextView)detailView.findViewById(R.id.url)).setText(kugouDetail.getUrl());
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("详情");
                            dialog.setView(detailView);
                            dialog.setPositiveButton("关闭",null);
                            dialog.show();
                        }else{
                            Toast.makeText(MainActivity.this, "错误码："+kugouDetail.getErrcode(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "获取失败："+e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
