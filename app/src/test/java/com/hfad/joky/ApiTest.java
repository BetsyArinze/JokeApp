package com.hfad.joky;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiTest {
    @Test
    public void getClient(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor()).build();

         ApiInterfaceTest service = new Retrofit.Builder()
                .baseUrl("https://joke-generator.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiInterfaceTest.class);
         System.out.println("About to start");
         retrofit2.Response<ApiInterfaceTest.JokeTest>res = null;
         try{
             res=service.getJokes().execute();
             System.out.printf("Response", res.body().getContent().toString());
         }catch (IOException e){
             e.printStackTrace();
             System.out.printf("Failed");
         }

    }
    public static class HeaderInterceptor implements Interceptor{

        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException{
            Request request = chain.request();
            return chain.proceed(request.newBuilder()
                    .addHeader("x-rapidapi-host","joke-generator.p.rapidapi.com")
                    .addHeader("x-rapidapi-key","23f9c1644bmshd78f5d74e337daep1f3ce3jsn7d3149ea6adc").build());

        }
    }

}
