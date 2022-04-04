package com.hfad.joky;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit = null;
    private static final  String apiKey = BuildConfig.ApiKey;
    private static final String apiHost = BuildConfig.ApiHost;

    public static ApiInterface getClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor()).build();

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://joke-generator.p.rapidapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        //create object for interface
        ApiInterface api = retrofit.create(ApiInterface.class);
        return api;

    }
    public static class HeaderInterceptor implements Interceptor{

        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException{
            Request request = chain.request();

            return chain.proceed(request.newBuilder()
                    .addHeader("x-rapidapi-host",apiHost)
                    .addHeader("x-rapidapi-key",apiKey).build());

        }
    }
}
