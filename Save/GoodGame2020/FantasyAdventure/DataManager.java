package com.GoodGame2020.FantasyAdventure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class DataManager extends SQLiteOpenHelper {
    private final static char KEY = '\u022b';
    private static DataManager dataManager;
    //== SQLite
    private static final String DATABASE_NAME = "myDatabase.db";
    private static final String DATABASE_TABLE = "myDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATA = "data";

    public DataManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dataManager = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_TABLE + "(" + KEY_ID
                + " integer primary key autoincrement," + KEY_NAME + " text NOT NULL," + KEY_DATA + " text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DATABASE_TABLE);
        onCreate(db);

    }

    public void setSQLValue(String key, String data){
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        String select_query= "SELECT *FROM " + DATABASE_TABLE ;
        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        int ID = -1;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(key)) ID = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        if (ID > -1){
            updateValue(key, data, ID);
        }
        else {
            sqLiteDatabase = this .getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, key);
            values.put(KEY_DATA, data);
            //inserting new row
            sqLiteDatabase.insert(DATABASE_TABLE, null , values);
            //close database connection
            sqLiteDatabase.close();
        }
    }

    public String getSQLValue(String key, String Default){
        String response = Default;
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        String select_query= "SELECT *FROM " + DATABASE_TABLE ;
        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        boolean has = false;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(key)){
                    response = cursor.getString(2);
                    has = true;
                }
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        if (!has){
            sqLiteDatabase = this .getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, key);
            values.put(KEY_DATA, Default);
            //inserting new row
            sqLiteDatabase.insert(DATABASE_TABLE, null , values);
            //close database connection
            sqLiteDatabase.close();
        }
        return response;
    }
    public class NoteModel {

        String ID;
        String title;
        String des;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

    }

    public ArrayList<NoteModel> getNotes() {
        ArrayList<NoteModel> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT *FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setID(cursor.getString(0));
                noteModel.setTitle(cursor.getString(1));
                noteModel.setDes( XOR(cursor.getString(2)));
                arrayList.add(noteModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void deleteValue(String key) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String select_query= "SELECT *FROM " + DATABASE_TABLE ;
        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        int ID = -1;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(key)) ID = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        if (ID > -1) sqLiteDatabase.delete(DATABASE_TABLE, KEY_ID+"=" + ID, null);
        sqLiteDatabase.close();
    }

    public void updateValue(String key, String data, int ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put(KEY_NAME, key);
        values.put(KEY_DATA, data);
        //updating row
        sqLiteDatabase.update(DATABASE_TABLE, values, KEY_ID+"=" + ID, null);
        sqLiteDatabase.close();
    }
    //=========== Unity calls
    public static void delValue(String key){
        dataManager.deleteValue(key);
    }

    public static void setValue(String key, String data){
        dataManager.setSQLValue(key, XOR(data));
    }

    public static String getValue(String key, String Default){
        return XOR(dataManager.getSQLValue(key, XOR(Default)));
    }

    /*public static void SaveData(String path , String[] data){
        File f = new File(path);
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            FileWriter out= new FileWriter(f);
            out.flush();
            for (int i = 0; i < data.length; i++)
                out.write(data[i]);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] LoadData(String path){
        File f = new File(path);
        LinkedList<String> data = new LinkedList<>();
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            Scanner in = new Scanner(f);
            while (in.hasNextLine())
                data.add(in.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] Data = new String[data.size()];
        for (int i = 0; i < Data.length; i++) {
            Data[i] = data.get(i);
        }
        return Data;
    }*/


    static String XOR(String data){
        char[] Data = data.toCharArray();
        for (int i = 0; i < Data.length; i++) {
            Data[i] = (char)(Data[i] ^ KEY);
        }
        return new String(Data);
    }

}
