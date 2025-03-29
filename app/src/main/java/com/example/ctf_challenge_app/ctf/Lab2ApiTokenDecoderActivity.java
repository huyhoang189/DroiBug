package com.example.ctf_challenge_app.ctf;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;

public class Lab2ApiTokenDecoderActivity extends AppCompatActivity {

    private static final String PREF_NAME = "app_data";
    private static final String API_TOKEN_KEY = "secure_api_token";


    // Token đã mã hóa (giá trị thật là: "s3cr3t_k3y_f0r")
    private static final String ENCODED_TOKEN = "czNjcjN0X2szeV9mMHI=";

    private TextView  tvFlag;
    private EditText etKey;
    private Button btnGenerateToken, btnVerifyKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_2_api_token_decoder);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvFlag = findViewById(R.id.tv_flag);
        etKey = findViewById(R.id.et_key);
        btnGenerateToken = findViewById(R.id.btn_generate_token);
        btnVerifyKey = findViewById(R.id.btn_verify_key);

        tvFlag.setVisibility(View.GONE);

        btnGenerateToken.setOnClickListener(v -> generateAndSaveToken());
        btnVerifyKey.setOnClickListener(v -> verifyKey());

    }

    private void generateAndSaveToken() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(API_TOKEN_KEY, ENCODED_TOKEN).apply();

        Toast.makeText(this, "Token API đã được lưu vào ứng dụng!", Toast.LENGTH_SHORT).show();
        etKey.setText("");
        tvFlag.setVisibility(View.GONE);
    }

    private void verifyKey() {
        String inputKey = etKey.getText().toString().trim();
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String encodedToken = prefs.getString(API_TOKEN_KEY, "");

        if (encodedToken.isEmpty()) {
            Toast.makeText(this, "Token chưa được tạo!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String decoded = new String(Base64.decode(encodedToken, Base64.DEFAULT)).trim();

            if (inputKey.equals(decoded)) {
                tvFlag.setText(FlagManager.getFlag("lab2"));
                tvFlag.setVisibility(View.VISIBLE);
                animateFlagTextView();
                Toast.makeText(this, "Chính xác! Bạn đã giải được token!", Toast.LENGTH_LONG).show();
            } else {
                tvFlag.setVisibility(View.GONE);
                Toast.makeText(this, "❌ Key không chính xác.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Lỗi giải mã token.", Toast.LENGTH_SHORT).show();
        }
    }

    private void animateFlagTextView() {
        tvFlag.animate().alpha(0f).setDuration(300).withEndAction(() ->
                tvFlag.animate().alpha(1f).setDuration(300));
    }
}