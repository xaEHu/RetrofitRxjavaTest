package com.xaehu.testview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText editText;
    private Button button;
    private ListView listView;
    private List<KugouSearch.DataBean.InfoBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    public void click(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mobilecdn.kugou.com/api/v3/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Map<String,Object> map = new HashMap(3);
        map.put("keyword",editText.getText().toString());
        map.put("page",1);
        map.put("pagesize",20);
        requestInterface.searchKugou(map).enqueue(new Callback<KugouSearch>() {
            @Override
            public void onResponse(Call<KugouSearch> call, Response<KugouSearch> response) {
                if(response.body().getData()!=null&&response.body().getData().getInfo()!=null){
                    list = response.body().getData().getInfo();
                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,list);
                    listView.setAdapter(adapter);
                }else{
                    Toast.makeText(MainActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KugouSearch> call, Throwable t) {
                Log.e("myout", "onFailure: "+t.getMessage());
                Toast.makeText(MainActivity.this, "连接失败:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        //http://m.kugou.com/app/i/getSongInfo.php?cmd=playInfo&hash=CB7EE97F4CC11C4EA7A1FA4B516A5D97
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://m.kugou.com/app/i/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        requestInterface.getDetail(list.get(position).getHash()).enqueue(new Callback<KugouDetail>() {
            @Override
            public void onResponse(Call<KugouDetail> call, Response<KugouDetail> response) {
                if(response.body().getErrcode()==0){
                    View detailView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_detail,null);
                    ((TextView)detailView.findViewById(R.id.song)).setText(response.body().getSongName());
                    ((TextView)detailView.findViewById(R.id.singer)).setText(response.body().getSingerName());
                    ((TextView)detailView.findViewById(R.id.filesize)).setText(String.valueOf(response.body().getFileSize()));
                    ((TextView)detailView.findViewById(R.id.time)).setText(String.valueOf(response.body().getTimeLength()));
                    ((TextView)detailView.findViewById(R.id.hash)).setText(response.body().getHash());
                    ((TextView)detailView.findViewById(R.id.url)).setText(response.body().getUrl());
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("详情");
                    dialog.setView(detailView);
                    dialog.setPositiveButton("关闭",null);
                    dialog.show();
                }else{
                    Toast.makeText(MainActivity.this, "错误码："+response.body().getErrcode(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KugouDetail> call, Throwable t) {
                Toast.makeText(MainActivity.this, "获取失败："+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
