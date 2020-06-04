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
    int position;
    Button play;
    boolean remove = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        new DataManager(this);
        position = getIntent().getIntExtra("Name",0);
        Name = DataManager.getValue("World"+position,"Error");
        TextView name = findViewById(R.id.PreName);
        name.setText(Name);
        ImageView Preview = findViewById(R.id.PreImage);
        Bitmap src = BitmapFactory.decodeFile(getFilesDir() + "/"+Name+"image.png");
        if (src!=null) Preview.setImageBitmap(src);
        else Preview.setImageResource(R.drawable.image);


        TextView dis = findViewById(R.id.PreDiscription);
        dis.setText("В кармане "+Integer.parseInt( DataManager.getValue(Name+"Money","0")) + " монет.");

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
            DataManager.setValue("NowWorld", Name);
            //Toast.makeText(this, "Sorry, it is test item", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(PreviewActivity.this,  UnityPlayerActivity.class);
            startActivity(i);

            //Log.i("Play button was pushed", "You start " + LevelDataArray.LevelData[ID].UnitySceneID + " level");
        } else if (view.getId() == R.id.Back) finish();
        else if (view.getId() == R.id.Remove) {
            if (remove) {
                int count = Integer.parseInt(DataManager.getValue("Worlds", "0"));
                String[] Worlds = new String[count];
                for (int i = position+1; i < count; i++) {
                    Worlds[i] = DataManager.getValue("World"+i,"Error");
                }
                for (int i = position; i < count-1; i++) {
                    DataManager.setValue("World"+i,Worlds[i+1]);
                }
                DataManager.delValue("World"+(count-1));
                DataManager.setValue("Worlds", ""+(count-1));
                DataManager.deleteWorld(Name);
                finish();
            } else {
                remove = true;
                Toast.makeText(this, R.string.PressAgain, Toast.LENGTH_LONG).show();
            }
        }
    }
}
