package com.GoodGame2020.FantasyAdventure;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

public class SettingsActivity extends Activity implements View.OnClickListener {

    CheckBox Shadow, Aim, Dynamic;
    SeekBar Far;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActionBar actionBar = getActionBar();
        //actionBar.hide ();

        setContentView(R.layout.activity_settings);
        ImageView im = findViewById(R.id.BackgroundMain);
        im.setImageResource(R.drawable.background);

        Shadow = findViewById(R.id.Shadow);
        Aim = findViewById(R.id.Aim);
        Dynamic = findViewById(R.id.Dynamic);

        Far = findViewById(R.id.Far);


        String[] Settings = DataManager.LoadData(getFilesDir()+"/Settings.data");
        Shadow.setChecked(Boolean.parseBoolean(Settings[0]));
        Aim.setChecked(Boolean.parseBoolean(Settings[1]));
        Dynamic.setChecked(Boolean.parseBoolean(Settings[2]));
        Far.setProgress(Integer.parseInt(Settings[3]));

        ImageButton Back = findViewById(R.id.Back);
        Back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Back) {
            String[] Settings = new String[4];
            Settings[0] = String.valueOf(Shadow.isChecked());
            Settings[1] = String.valueOf(Aim.isChecked());
            Settings[2] = String.valueOf(Dynamic.isChecked());
            Settings[3] = String.valueOf(Far.getProgress()<12?12:Far.getProgress());


            DataManager.SaveData(getFilesDir()+"/Settings.data",Settings);
            finish();
        }

    }

}