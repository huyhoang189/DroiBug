package com.example.ctf_challenge_app.models;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.utils.JsonMapper;

import org.json.JSONObject;

public class ChallengeMapper implements JsonMapper<Challenge> {
    @Override
    public Challenge map(JSONObject jsonObject) throws Exception {
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        String id = jsonObject.getString("id");

        // Xử lý imageId, giả sử ta có hàm ánh xạ
        int imageResId = 0; // Mặc định
        if (jsonObject.has("imageId")) {
            String imageId = jsonObject.getString("imageId");
            imageResId = getResourceIdFromImageId(imageId);
        }

        return new Challenge(id, imageResId, title, description);
    }


    private int getResourceIdFromImageId(String imageId) {
        // Ánh xạ giả lập, thay bằng logic thực tế trong Android
        switch (imageId) {
            case "ic_credential": return R.drawable.ic_credential;
            case "ic_auth": return R.drawable.ic_auth;
            case "ic_input_validation": return R.drawable.ic_input_validation;
            case "ic_privacy": return R.drawable.ic_privacy;
            case "ic_binary": return R.drawable.ic_binary;
            case "ic_data_storage": return R.drawable.ic_data_storage;
            case "ic_crypto": return R.drawable.ic_crypto;
            default: return R.drawable.challenge;
        }
    }
}
