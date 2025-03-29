package com.example.ctf_challenge_app.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public <T> List<T> parseArray(String jsonString, JsonMapper<T> mapper) {
        List<T> result = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T item = mapper.map(jsonObject);
                result.add(item);

                // Xử lý children nếu có
                if (jsonObject.has("children")) {
                    JSONArray childrenArray = jsonObject.getJSONArray("children");
                    for (int j = 0; j < childrenArray.length(); j++) {
                        JSONObject childObject = childrenArray.getJSONObject(j);
                        T childItem = mapper.map(childObject);
                        result.add(childItem);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}


