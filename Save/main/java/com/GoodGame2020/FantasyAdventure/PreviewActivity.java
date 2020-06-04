package com.GoodGame2020.FantasyAdventure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unity3d.player.UnityPlayerActivity;

public class PreviewActivity extends Activity implements View.OnClickListener {
    private String Name;
    Button play;
    boolean remove = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Name = getIntent().getStringExtra("Name");
        TextView name = findViewById(R.id.PreName);
        name.setText(Name);
        ImageView Preview = findViewById(R.id.PreImage);
        Bitmap src = BitmapFactory.decodeFile(getFilesDir() + "/"+Name+"image.data");
        Preview.setImageBitmap(src);


        ImageView im1 = findViewById(R.id.BackgroundMain);
        im1.setImageResource(R.drawable.background);
        play = findViewById(R.id.Play);
        ImageButton back = findViewById(R.id.Back);
        play.setOnClickListener(this);
        back.setOnClickListener(this);
        Button remove = findViewById(R.id.Remove);
        remove.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Play) {
            //TODO connect UnityPlayer
            String[] a =  new String[1];
            a[0] = Name;
            DataManager.SaveData(getFilesDir() + "/NowWorld.data", a);
            //Toast.makeText(this, "Sorry, it is test item", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(PreviewActivity.this,  UnityPlayerActivity.class);
            startActivity(i);

            //Log.i("Play button was pushed", "You start " + LevelDataArray.LevelData[ID].UnitySceneID + " level");
        } else if (view.getId() == R.id.Back) finish();
        else if (view.getId() == R.id.Remove) {
            if (remove) {
                String[] Worlds1 = DataManager.LoadData(getFilesDir() + "/Worlds.data");
                String[] Worlds = new String[Worlds1.length - 1];
                int shift = 0;
                for (int i = 0; i < Worlds1.length; i++) {
                    if (Worlds1[i].equals(Name)) shift = i;
                }
                for (int i = 0; i < shift; i++) {
                    Worlds[i] = Worlds1[i];
                }
                for (int i = shift; i < Worlds.length; i++) {
                    Worlds[i] = Worlds1[i + 1];
                }
                DataManager.SaveData(getFilesDir() + "/Worlds.data", Worlds);
                finish();
            } else {
                remove = true;
                Toast.makeText(this, R.string.PressAgain, Toast.LENGTH_LONG).show();
            }
        }
    }
}
