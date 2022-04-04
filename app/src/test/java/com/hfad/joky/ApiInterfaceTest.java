package com.hfad.joky;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiInterfaceTest {
    @GET("generate-joke")
    Call<JokeTest>getJokes();

    class JokeTest {
        @Test
        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getCategory() {
            return category;
        }

        private String title, content, category;

    }
}

