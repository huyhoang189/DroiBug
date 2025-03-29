package com.example.ctf_challenge_app.ctf;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;

public class Lab16InsecureStorageActivity extends AppCompatActivity {

    private static final String PREF_NAME = "user_data";
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin123";

    private EditText usernameEdt, passwordEdt;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab16_insecure_storage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        usernameEdt = findViewById(R.id.username);
        passwordEdt = findViewById(R.id.password);
        resultText = findViewById(R.id.result_text);
        Button loginBtn = findViewById(R.id.login_btn);

        // Lưu dữ liệu sẵn (giả lập lỗi dev)
        saveDefaultCredentials();

        loginBtn.setOnClickListener(v -> {
            String inputUser = usernameEdt.getText().toString().trim();
            String inputPass = passwordEdt.getText().toString().trim();

            SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            String savedUser = prefs.getString("username", "");
            String savedPass = prefs.getString("password", "");

            if (inputUser.equals(savedUser) && inputPass.equals(savedPass)) {
                resultText.setText(FlagManager.getFlag("lab16"));
            } else {
                resultText.setText("Sai thông tin đăng nhập!");
            }
        });
    }

    private void saveDefaultCredentials() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", DEFAULT_USERNAME);
        editor.putString("password", DEFAULT_PASSWORD);
        editor.apply();
    }
}