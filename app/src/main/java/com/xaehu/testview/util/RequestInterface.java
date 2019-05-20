package com.xaehu.testview.util;

import com.xaehu.testview.bean.KugouDetail;
import com.xaehu.testview.bean.KugouSearch;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RequestInterface {

    @GET("song?format=json")
    Call<KugouSearch> searchKugou(@QueryMap Map<String,Object> map);

    @FormUrlEncoded
    @POST("getSongInfo.php?cmd=playInfo")
    Call<KugouDetail> getDetail(@Field("hash") String hash);
}
