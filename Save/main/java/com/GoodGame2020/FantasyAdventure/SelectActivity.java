package com.GoodGame2020.FantasyAdventure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class SelectActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    String[] Worlds1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        Worlds1 = DataManager.LoadData(getFilesDir()+"/Worlds.data");
        Level[] Worlds = new Level[Worlds1.length];
        for (int i = 0; i < Worlds.length; i++) {
            Worlds[i] = new Level(R.drawable.image, Worlds1[i], 0, "Тут моя фантазия иссякла");
        }

        LevelAdapter adapter = new LevelAdapter(this, Worlds);
        ListView lv = (ListView) findViewById(R.id.List);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        ImageView im = findViewById(R.id.BackgroundMain);
        im.setImageResource(R.drawable.background);

        ImageButton Back = findViewById(R.id.Back);
        Back.setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Worlds1 = DataManager.LoadData(getFilesDir()+"/Worlds.data");
        Level[] Worlds = new Level[Worlds1.length];
        for (int i = 0; i < Worlds.length; i++) {
            Worlds[i] = new Level(R.drawable.image, Worlds1[i], 0, "Тут моя фантазия иссякла");
        }

        LevelAdapter adapter = new LevelAdapter(this, Worlds);
        ListView lv = (ListView) findViewById(R.id.List);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Worlds1 = DataManager.LoadData(getFilesDir()+"/Worlds.data");
        Level[] Worlds = new Level[Worlds1.length];
        for (int i = 0; i < Worlds.length; i++) {
            Worlds[i] = new Level(R.drawable.image, Worlds1[i], 0, "Тут моя фантазия иссякла");
        }

        LevelAdapter adapter = new LevelAdapter(this, Worlds);
        ListView lv = (ListView) findViewById(R.id.List);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Back)  finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent i = new Intent(SelectActivity.this, PreviewActivity.class);
        i.putExtra("Name", Worlds1[position]);
        startActivity(i);
    }


}
