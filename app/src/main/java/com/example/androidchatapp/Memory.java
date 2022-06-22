package com.example.androidchatapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public final class Memory {

    public static void saveData(String data, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("data.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void savePass(String data, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("namee.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getData(Context context){
        String data= "";
        try {
            FileInputStream fis = context.openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getNickname(Context context){
        String data= "";
        try {
            FileInputStream fis = context.openFileInput("nickname.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getPass(Context context){
        String data= "";
        try {
            FileInputStream fis = context.openFileInput("pass.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
