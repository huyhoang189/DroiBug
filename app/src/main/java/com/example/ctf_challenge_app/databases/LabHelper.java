package com.example.ctf_challenge_app.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LabHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "labs.db";
    private static final int DATABASE_VERSION = 3;

    // Bảng LABS
    private static final String TABLE_LABS = "labs";
    private static final String CREATE_TABLE_LABS = "CREATE TABLE " + TABLE_LABS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "lab_key TEXT UNIQUE NOT NULL, " +  // Key duy nhất cho mỗi Lab
            "title TEXT NOT NULL, " +
            "description TEXT, " +
            "status INTEGER DEFAULT 0, " +
            "challenge_type TEXT NOT NULL);";

    public LabHelper (Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LABS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABS);
        onCreate(db);
    }

}
