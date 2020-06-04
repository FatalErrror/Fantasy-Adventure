package com.GoodGame2020.FantasyAdventure;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsActivity extends Activity implements View.OnClickListener, Callback {

    private final String URL = "https://raw.githubusercontent.com/FatalErrror/Server/master/";

    OkHttpClient client = new OkHttpClient();

    void enqueue(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(callback);
    }

    NewsAdapter adapter;
    ListView news;
    News[] News;
    Context AdContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActionBar actionBar = getActionBar();
        //actionBar.hide ();

        setContentView(R.layout.activity_news);
        ImageView im = findViewById(R.id.BackgroundMain);
        im.setImageResource(R.drawable.background);

        News[] loading = new News[1];
        loading[0] = new News("Loading...", "Loading...");
        adapter = new NewsAdapter(this, loading);
        AdContext = this;
        news = findViewById(R.id.List);
        news.setAdapter(adapter);
        ImageButton Back = findViewById(R.id.Back);
        Back.setOnClickListener(this);
        enqueue(URL+"News.txt", this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Back) {

            finish();
        }

    }


    @Override
    public void onFailure(Call call, IOException e) {
        call.cancel();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        final String myResponse = response.body().string();
        String[] Names = myResponse.split("\n");
        News = new News[Names.length];
        for (int i = 0; i < Names.length; i++) {
            News[i] = new News(Names[i], "Loading...");
            enqueue(URL + Names[i] + ".txt", new MyCallback(i, News, new delegate() {
                @Override
                public void update() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new NewsAdapter(AdContext, News);
                            news.setAdapter(adapter);
                        }
                    });
                }
            }));
        }
    }
}

class MyCallback implements Callback {


    public MyCallback(int position, News[] news, delegate call) {
        this.position = position;
        News = news;
        Call = call;
    }

    int position;
    News[] News;
    delegate Call;

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        String ans = response.body().string();
        if (ans.split("\n", 2)[0].contains("http")){
            String[] answers = ans.split("\n", 3);
            News[position] = new News(answers[0], answers[1], answers[2]);
        }else {
            String[] answers = ans.split("\n", 2);
            News[position] = new News(answers[0], answers[1]);
        }
        Call.update();
    }
}

interface delegate{
    void update();
}