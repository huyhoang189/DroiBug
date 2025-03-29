package com.example.ctf_challenge_app.models;

import com.example.ctf_challenge_app.utils.JsonMapper;

import org.json.JSONObject;

public class Challenge {
    private String id;
    private int imageResId;
    private String title;
    private String description;

    public Challenge(String id, int imageResId, String title, String description) {
        this.id = id;
        this.imageResId = imageResId;
        this.title = title;
        this.description = description;
    }

    public String getId( ) {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


