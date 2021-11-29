package com.example.flickrfindr.network;

import com.example.flickrfindr.model.ImageResult;
import com.example.flickrfindr.model.PhotoWrapper;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIService {
    @GET("/services/rest")
    Call<PhotoWrapper> getImages(
            @QueryMap Map<String, String> params);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
