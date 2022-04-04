package com.hfad.joky;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;

import java.util.Locale;

public class tellJoke extends AppCompatActivity {
    Jokes jokesData;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tell_joke);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getjokesData();
    }

    private void getjokesData() {

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        (Api.getClient().getJokes()).enqueue(new Callback<Jokes>() {
            @Override
            public void onResponse(Call<Jokes> call, Response<Jokes> response) {
                Log.d("responseGET",response.body().getTitle());
                jokesData = response.body();
                setData();
                readJoke();
            }

            @Override
            public void onFailure(Call<Jokes> call, Throwable t) {
                //if error occurs
                Toast.makeText(tellJoke.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void setData(){
        TextView title = findViewById(R.id.title);
        title.setText(jokesData.getTitle());

        TextView content = findViewById(R.id.content);
        content.setText(jokesData.getContent());
    }

    public void readJoke(){
        String toSpeak = jokesData.getContent();
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}
