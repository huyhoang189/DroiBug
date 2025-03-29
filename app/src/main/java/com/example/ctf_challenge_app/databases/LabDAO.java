package com.example.ctf_challenge_app.databases;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ctf_challenge_app.models.Lab;

import java.util.ArrayList;
import java.util.List;

public class LabDAO {
    private LabHelper labDbHelper;

    public LabDAO(Context ctx){
        labDbHelper = new LabHelper(ctx);
    }

    // Thêm một Lab mới
    public long insertLab(String labKey, String title, String description, int status, String challengeType) {
        SQLiteDatabase db = labDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lab_key", labKey); // Thêm key
        values.put("title", title);
        values.put("description", description);
        values.put("status", status);
        values.put("challenge_type", challengeType);
        return db.insert("labs", null, values);
    }

    // Lấy danh sách Lab theo loại Challenge
    public List<Lab> getLabsByChallengeType(String challengeType) {
        List<Lab> labs = new ArrayList<>();
        SQLiteDatabase db = labDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM labs WHERE challenge_type = ?", new String[]{challengeType});

        while (cursor.moveToNext()) {
            @SuppressLint("Range") String labKey = cursor.getString(cursor.getColumnIndex("lab_key"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));
            @SuppressLint("Range") int status = cursor.getInt(cursor.getColumnIndex("status"));

            labs.add(new Lab(labKey, title, description, status, challengeType));
        }

        cursor.close();
        return labs;
    }

    // Cập nhật trạng thái hoàn thành của Lab
    public void updateLabStatus(String labKey, int status) {
        SQLiteDatabase db = labDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", status);
        db.update("labs", values, "lab_key = ?", new String[]{String.valueOf(labKey)});
    }
}
