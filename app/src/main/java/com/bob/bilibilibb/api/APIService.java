package com.bob.bilibilibb.api;

import com.bob.bilibilibb.bean.VideoProfile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface APIService {

    @GET("list")
    Observable<ResponseBody> getVideoList(
            @Query("appkey") String appkey,
            @Query("tid") String tid,
            @Query("page") String page,
            @Query("pagesize") String pagesize,
            @Query("order") String order);

    @GET("view")
    Call<VideoProfile> getVideoProfile(
            @Query("appkey") String appkey,
            @Query("id") String avId,
            @Query("page") String page,
            @Query("fav") String fav);

    @GET("view")
    Observable<ResponseBody> getVideoProfileObservable(
            @Query("appkey") String appkey,
            @Query("id") String avId,
            @Query("page") String page,
            @Query("fav") String fav);

    @GET("playurl")
    Call<ResponseBody> getVideoSource(
            @Query("appkey") String appkey,
            @Query("cid") String cid);

}
