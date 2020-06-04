package com.GoodGame2020.FantasyAdventure;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ChoseActivity extends Activity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActionBar actionBar = getActionBar();
        //actionBar.hide ();

        setContentView(R.layout.activity_chose);
        ImageView im = findViewById(R.id.BackgroundMain);
        im.setImageResource(R.drawable.background);

        Button Play = findViewById(R.id.New);
        Play.setOnClickListener(this);
        Button News = findViewById(R.id.Load);
        News.setOnClickListener(this);
        ImageButton Back = findViewById(R.id.Back);
        Back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.New) {

            Intent i = new Intent(ChoseActivity.this,CreateActivity.class);
            startActivity(i);
        } else
        if(view.getId() == R.id.Load) {

            Intent i = new Intent(ChoseActivity.this,SelectActivity.class);
            startActivity(i);
        } else
        if(view.getId() == R.id.Back) {

            finish();
        }

    }

}
