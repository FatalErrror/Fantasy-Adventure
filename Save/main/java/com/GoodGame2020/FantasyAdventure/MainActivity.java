package com.GoodGame2020.FantasyAdventure;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActionBar actionBar = getActionBar();
        //actionBar.hide ();

        setContentView(R.layout.activity_main);
        ImageView im = findViewById(R.id.BackgroundMain);
        im.setImageResource(R.drawable.background);

        Button Play = findViewById(R.id.Play);
        Play.setOnClickListener(this);
        Play.setText(getFilesDir().getAbsolutePath());
        Button News = findViewById(R.id.News);
        News.setOnClickListener(this);
        Button Settings = findViewById(R.id.Settings);
        Settings.setOnClickListener(this);
        Button Quit = findViewById(R.id.Quit);
        Quit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Play) {

            Intent i = new Intent(MainActivity.this,ChoseActivity.class);
            startActivity(i);
        } else
        if(view.getId() == R.id.News) {

            Intent i = new Intent(MainActivity.this,NewsActivity.class);
            startActivity(i);
        } else
        if(view.getId() == R.id.Settings) {

            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(i);
        } else
        if(view.getId() == R.id.Quit) {
            finish();
        }

    }

}
