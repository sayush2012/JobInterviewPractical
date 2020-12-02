package com.example.jobinterviewpractical.activity.retrofit;

import com.example.jobinterviewpractical.activity.model.DetailBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET(".")
    Call<DetailBean> moviedata(@Query("s") String s,
                         @Query("apikey") String apikey);
}
