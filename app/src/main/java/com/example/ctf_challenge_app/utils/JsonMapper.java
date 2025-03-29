package com.example.ctf_challenge_app.utils;

import org.json.JSONObject;

public interface JsonMapper<T> {
    T map(JSONObject jsonObject) throws Exception;
}
