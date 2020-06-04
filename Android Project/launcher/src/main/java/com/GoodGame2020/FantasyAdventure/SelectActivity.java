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
    //String[] Worlds1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        new DataManager(this);
        int count = Integer.parseInt(DataManager.getValue("Worlds", "0"));
        Level[] Worlds = new Level[count];
        for (int i = 0; i < count; i++) {
            Worlds[i] = new Level(R.drawable.image, DataManager.getValue("World"+i,"Error"), 0, "Тут моя фантазия иссякла");
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
        int count = Integer.parseInt(DataManager.getValue("Worlds", "0"));
        Level[] Worlds = new Level[count];
        for (int i = 0; i < count; i++) {
            Worlds[i] = new Level(R.drawable.image, DataManager.getValue("World"+i,"Error"), 0, "Тут моя фантазия иссякла");
        }
        LevelAdapter adapter = new LevelAdapter(this, Worlds);
        ListView lv = (ListView) findViewById(R.id.List);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int count = Integer.parseInt(DataManager.getValue("Worlds", "0"));
        Level[] Worlds = new Level[count];
        for (int i = 0; i < count; i++) {
            Worlds[i] = new Level(R.drawable.image, DataManager.getValue("World"+i,"Error"), 0, "Тут моя фантазия иссякла");
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
        i.putExtra("Name", position);
        startActivity(i);
    }


}
