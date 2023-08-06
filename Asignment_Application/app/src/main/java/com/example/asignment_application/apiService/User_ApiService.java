package com.example.asignment_application.apiService;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface User_ApiService {
    User_ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(User_ApiService.class);

    @FormUrlEncoded
    @POST("signup")
    Call<Boolean> signUP(@Field("name") String name,
                         @Field("email") String email,
                         @Field("password") String pass

    );

    @FormUrlEncoded
    @POST("login")
    Call<Boolean> login(@Field("name") String name,
                           @Field("password") String pass
    );
}
