package com.GoodGame2020.FantasyAdventure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class DataManager {
    /*Files
    Worlds.data - Список миров
    NowWorld.data - данные для загрузки мира
    Settings.data - настройки
    */


    static char Key = '\u022b';


    static String XOR(char key,String data){
        char[] Data = data.toCharArray();
        for (int i = 0; i < Data.length; i++) {
            Data[i] = (char)(Data[i] ^ key);
        }
        return new String(Data);
    }

    static void SaveData(String path, char key, String[] data){
        File f = new File(path);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String Data = "";
        for (int i = 0; i < data.length; i++) {
            Data += XOR(key, data[i])+"\n";
        }
        try {
            FileWriter out = new FileWriter(f);
            out.write( Data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String[] LoadData(String path, char key){
        File f = new File(path);
        LinkedList<String> data = new LinkedList<>();
        try {
            Scanner in = new Scanner(f);
            while (in.hasNextLine()){
                data.add(XOR(key, in.nextLine()));
            }
            String[] a = new String[0];
            return data.toArray(a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new String[] {""};
        }

    }

    static void SaveData(String path, String[] data){
        File f = new File(path);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String Data = "";
        for (int i = 0; i < data.length; i++) {
            Data += data[i]+"\n";
        }
        try {
            FileWriter out = new FileWriter(f);
            out.flush();
            out.write(Data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String[] LoadData(String path){
        File f = new File(path);
        LinkedList<String> data = new LinkedList<>();
        try {
            Scanner in = new Scanner(f);
            while (in.hasNextLine()){
                data.add(in.nextLine());
            }
            String[] a = new String[0];
            return data.toArray(a);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new String[] {""};
        }

    }



}
