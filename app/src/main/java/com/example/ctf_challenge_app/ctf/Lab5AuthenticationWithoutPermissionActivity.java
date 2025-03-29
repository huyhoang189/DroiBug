package com.example.ctf_challenge_app.ctf;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;

public class Lab5AuthenticationWithoutPermissionActivity extends AppCompatActivity {




    private static final String PREF_NAME = "user_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab5_authentication_without_permission);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button loginBtn = findViewById(R.id.login_btn);
        Button resetBtn = findViewById(R.id.reset_permission_btn);
        TextView flagText = findViewById(R.id.flag_text);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (!prefs.contains("is_admin")) {
            prefs.edit().putBoolean("is_admin", false).apply();
        }

        loginBtn.setOnClickListener(v -> {
            boolean isAdmin = prefs.getBoolean("is_admin", false);

            if (isAdmin) {
                flagText.setText(FlagManager.getFlag("lab5"));
            } else {
                flagText.setText("");
                Toast.makeText(this, "Bạn không có quyền admin!", Toast.LENGTH_SHORT).show();
            }
        });

        resetBtn.setOnClickListener(v -> {
            prefs.edit().putBoolean("is_admin", false).apply();
            Toast.makeText(this, "Đã reset lại quyền!", Toast.LENGTH_SHORT).show();
        });


    }
}