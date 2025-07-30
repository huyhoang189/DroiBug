package com.example.ctf_challenge_app.ctf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

public class Lab3LoginLogActivity extends AppCompatActivity {

    private static final String TAG = "Lab_3_log";
    private final String REAL_USER = "admin";
    private final String REAL_PASS = "superpass123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_login_log);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.login_btn);
        Button logGenBtn = findViewById(R.id.btn_generate_log);
        TextView flagText = findViewById(R.id.flag_text);

        // Nút Tạo Log (Gây lộ user/password thật)
        logGenBtn.setOnClickListener(v -> {
            Log.d(TAG, "Leaked credentials -> user: " + REAL_USER + ", pass: " + REAL_PASS);
            Toast.makeText(this, "Đã tạo log!", Toast.LENGTH_SHORT).show();
        });

        // Nút Đăng nhập
        loginBtn.setOnClickListener(v -> {
            String inputUser = usernameEditText.getText().toString().trim();
            String inputPass = passwordEditText.getText().toString().trim();

            if (inputUser.equals(REAL_USER) && inputPass.equals(REAL_PASS)) {
                flagText.setText(FlagManager.getFlag("lab3"));

            } else {
                flagText.setText("");
                Toast.makeText(this, "Sai thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab3LoginLogActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_3");
            startActivity(intent);
        });
    }
}