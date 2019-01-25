package com.example.testexam.API;

import com.example.testexam.API.Model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://street-art-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("artworks")
    Call<List<Example>> getData();

}
