package com.example.asignment_application.apiService;

import com.example.asignment_application.model.Sach;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Sach_PostService {
    @FormUrlEncoded
    @POST("addBook")
    Call<ArrayList<Sach>> postData(
            @Field("name") String name,
            @Field("price") String price
    );
}
