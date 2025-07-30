package com.example.ctf_challenge_app.ctf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.HelpActivity;
import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Lab6FridaForAuthenticationActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab6_frida_for_authentication);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button loginBtn = findViewById(R.id.login_btn);
        TextView flagText = findViewById(R.id.flag_text);


        loginBtn.setOnClickListener(v -> {
            String token = getAuthToken(); // Lấy token giả lập
            if (checkToken(token)) {
                flagText.setText(FlagManager.getFlag("lab6"));

            } else {
                flagText.setText("");
                Toast.makeText(this, "Token không hợp lệ!", Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab6FridaForAuthenticationActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_6");
            startActivity(intent);
        });
    }


    // Hàm giả lập trả về token của người dùng (hardcoded hoặc bất kỳ)
    public String getAuthToken() {
        return "admin-token";  // Không phải token hợp lệ
    }

    // Kiểm tra token
    public boolean checkToken(String token) {
        return token.equals("admin-token");
    }





}