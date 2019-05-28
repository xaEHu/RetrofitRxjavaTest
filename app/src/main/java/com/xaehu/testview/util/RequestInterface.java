package com.xaehu.testview.util;

import com.xaehu.testview.bean.KugouDetail;
import com.xaehu.testview.bean.KugouSearch;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RequestInterface {

    @GET("song?format=json")
    Observable<KugouSearch> searchKugou(@QueryMap Map<String,Object> map);

    @FormUrlEncoded
    @POST("getSongInfo.php?cmd=playInfo")
    Observable<KugouDetail> getDetail(@Field("hash") String hash);
}
