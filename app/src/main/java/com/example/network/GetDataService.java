package com.example.network;

import com.example.model.Item;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("/api/v1/store")
    Call<List<Item>> getItems(@Query("region") String region);
}
