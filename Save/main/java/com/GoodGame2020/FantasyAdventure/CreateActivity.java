package com.GoodGame2020.FantasyAdventure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.unity3d.player.UnityPlayerActivity;

public class CreateActivity extends Activity implements View.OnClickListener {

    Button play;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ImageView im1 = findViewById(R.id.BackgroundMain);
        im1.setImageResource(R.drawable.background);
        play = findViewById(R.id.Play);
        ImageButton back = findViewById(R.id.Back);
        play.setOnClickListener(this);
        back.setOnClickListener(this);
        name = findViewById(R.id.GameName);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Play) {
            //TODO connect UnityPlayer
            String[] Worlds1 = DataManager.LoadData(getFilesDir()+"/Worlds.data");
            String[] Worlds = new String[Worlds1.length+1];
            for (int i = 0; i < Worlds1.length; i++) {
                Worlds[i] = Worlds1[i];
            }
            Worlds[Worlds.length-1] = name.getText().toString();
            DataManager.SaveData(getFilesDir()+"/Worlds.data",Worlds);
            String[] a =  new String[1];
            a[0] = name.getText().toString();
            DataManager.SaveData(getFilesDir() + "/NowWorld.data", a);
            //Toast.makeText(this, "Sorry, it is test item", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(CreateActivity.this,  UnityPlayerActivity.class);
            startActivity(i);
            //Log.i("Play button was pushed", "You start " + LevelDataArray.LevelData[ID].UnitySceneID + " level");
        }
        if(view.getId() == R.id.Back) finish();
    }
}
