package com.example.ctf_challenge_app.utils;

import android.content.Context;

import java.io.InputStream;
import java.io.IOException;

public class JsonFileReader {

    public static String readJsonFromFile(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
