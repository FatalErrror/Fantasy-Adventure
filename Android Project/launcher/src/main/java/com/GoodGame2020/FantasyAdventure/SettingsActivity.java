package com.GoodGame2020.FantasyAdventure;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SettingsActivity extends Activity implements View.OnClickListener {

    CheckBox Shadow, Aim, Dynamic, Weapon;
    SeekBar Far;
    DataManager dataManager;

    EditText CMD;
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
        Weapon = findViewById(R.id.Weapon);

        Far = findViewById(R.id.Far);

        dataManager = new DataManager(this);

        ArrayList<DataManager.NoteModel> l = dataManager.getNotes();

        for (int i = 0; i < l.size(); i++) {
            Log.i("SQL", "ID: "+l.get(i).ID+" name: "+l.get(i).title+" data: "+ l.get(i).des);
        }
        try {
            Shadow.setChecked(Boolean.parseBoolean(DataManager.getValue("Shadow", "false")));
            Aim.setChecked(Boolean.parseBoolean(DataManager.getValue("Aim", "false")));
            Dynamic.setChecked(Boolean.parseBoolean(DataManager.getValue("Dynamic", "false")));
            Far.setProgress(Integer.parseInt(DataManager.getValue("Far", "50")));
            Weapon.setChecked(Boolean.parseBoolean(DataManager.getValue("Weapon", "false")));
        }
        catch (Exception e){

        }


        ImageButton Back = findViewById(R.id.Back);
        Back.setOnClickListener(this);
        Button dev = findViewById(R.id.DevMod);
        dev.setOnClickListener(this);
        CMD = findViewById(R.id.CMD);
        CMD.setClickable(false);
        CMD.setEnabled(false);
        CMD.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                            try {
                                String[] in = CMD.getText().toString().split("/");
                                Log("\""+CMD.getText().toString()+"\" was setup");
                                CMD.setText("");
                                CMD.setClickable(false);
                                CMD.setEnabled(false);
                                CMD.setAlpha(0);
                                switch (in[0]) {
                                    case "SQLdel"://SQLdel/<name>
                                        DataManager.delValue(in[1]);
                                        break;
                                    case "SQLdelID"://SQLdelID/<ID>
                                        DataManager.delValue(Integer.parseInt(in[1]));
                                        break;
                                    case "SQLdelWorld"://SQLdelWorld/<name>
                                        DataManager.deleteWorld(in[1]);
                                        break;
                                    case "SQLset"://SQLset/<name>/<data>
                                        DataManager.setValue(in[1], in[2]);
                                        break;
                                    case "SQLget"://SQLget/<name>/<data>
                                        LongLog("Value: " + DataManager.getValue(in[1], in[2]));
                                        break;
                                    case "SQLall"://SQLall
                                        String a = "";
                                        ArrayList<DataManager.NoteModel> l = dataManager.getNotes();
                                        for (int i = 0; i < l.size(); i++) {
                                             a += "ID: "+l.get(i).ID+" name: "+l.get(i).title+" data: "+ l.get(i).des+"\n";
                                        }
                                        LongLog(a);
                                        LongLog(a);
                                        LongLog(a);
                                        break;
                                    case "Log"://Log/<text>
                                        LongLog("massage: "+in[1]);
                                        break;
                                    case "Help"://Help
                                        String b = "Help -- Список команд" +"\n" +
                                                "SQLdel/<name> -- Удалить значение" +"\n" +
                                                "SQLdelID/<ID> -- Удалить значение по ID" +"\n" +
                                                "SQLdelWorld/<name> -- Удалить данные мира" +"\n" +
                                                "SQLset/<name>/<data> -- Записать значение" +"\n" +
                                                "SQLget/<name>/<data> -- Получить значение" +"\n" +
                                                "SQLall -- Вся база данных" +"\n" +
                                                "Log/<text> -- Сообщение" +"\n" ;
                                        LongLog(b);
                                        LongLog(b);
                                        LongLog(b);
                                        break;

                                    default:
                                        Log("Invalid command");
                                        break;
                                }
                                Log("Successful");
                            } catch (Exception e){
                                Log("Error");
                                e.printStackTrace();
                            }

                        }
                        return false; // pass on to other listeners.
                    }
                });
    }

    public void Log(String text){
        Log.i("CMD", text);
        Toast.makeText(SettingsActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public void LongLog(String text){
        Log.i("CMD", text);
        Toast.makeText(SettingsActivity.this, text, Toast.LENGTH_LONG).show();
    }
    int block = 0;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Back) {

            DataManager.setValue("Shadow", String.valueOf(Shadow.isChecked()));
            DataManager.setValue("Aim", String.valueOf(Aim.isChecked()));
            DataManager.setValue("Dynamic", String.valueOf(Dynamic.isChecked()));
            DataManager.setValue("Far", String.valueOf(Far.getProgress() < 12 ? 12 : Far.getProgress()));
            DataManager.setValue("Weapon", String.valueOf(Weapon.isChecked()));

            finish();
        }
        if (view.getId() == R.id.DevMod && Far.getProgress() == 0){
            if (block<10 ) block++;
            else {
                CMD.setClickable(true);
                CMD.setEnabled(true);
                CMD.setAlpha(1);
                Log("CMD is started");
            }

        }

    }

}