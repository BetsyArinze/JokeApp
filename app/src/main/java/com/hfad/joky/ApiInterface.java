package com.hfad.joky;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("generate-joke")
    Call<Jokes> getJokes();
}
