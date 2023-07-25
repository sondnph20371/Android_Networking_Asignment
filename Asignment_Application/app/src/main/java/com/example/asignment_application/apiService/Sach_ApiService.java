package com.example.asignment_application.apiService;

import com.example.asignment_application.model.Sach;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Sach_ApiService {


    Sach_ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Sach_ApiService.class);

    @GET("books")
    Call<ArrayList<Sach>> getBooks();

    @GET("delete/{id}")
    Call<ArrayList<Sach>> deleteBooks(@Path("id") String id);

    @FormUrlEncoded
    @POST("update")
    Call<ArrayList<Sach>> updateBooks(@Field("id") String id,
                                      @Field("name") String name,
                                      @Field("price") String price
    );

    @FormUrlEncoded
    @POST("addBook")
    Call<ArrayList<Sach>> postData(
            @Field("name") String name,
            @Field("price") String price
    );


}
