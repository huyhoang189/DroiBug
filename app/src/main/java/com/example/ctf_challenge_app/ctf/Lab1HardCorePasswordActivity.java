package com.example.ctf_challenge_app.ctf;

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

public class Lab1HardCorePasswordActivity extends AppCompatActivity {

    private static final String HARDCODED_PASSWORD = "SecretPass123";

    private EditText passwordInput;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab1_hard_core_password);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        passwordInput = findViewById(R.id.password_input);
        resultText = findViewById(R.id.result_text);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String input = passwordInput.getText().toString().trim();

            if (input.isEmpty()) {
                resultText.setText("Vui lòng nhập mật khẩu.");
                return;
            }

            if (input.equals(HARDCODED_PASSWORD)) {
                resultText.setText(FlagManager.getFlag("lab1"));
            } else {
                resultText.setText("Mật khẩu không đúng.");
            }
        });
    }
}