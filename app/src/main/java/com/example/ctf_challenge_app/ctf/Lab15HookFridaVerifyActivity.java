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

public class Lab15HookFridaVerifyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab15_hook_frida_verify);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText codeInput = findViewById(R.id.input_code);
        TextView result = findViewById(R.id.result_text);
        Button checkBtn = findViewById(R.id.btn_verify_key);

        checkBtn.setOnClickListener(v -> {
            String userInput = codeInput.getText().toString();
            if (verifyCode(userInput)) {
                result.setText(FlagManager.getFlag("lab15"));
            } else {
                result.setText("Mã không hợp lệ");
            }
        });
    }

    // Hàm này bị ẩn logic, trả về false mặc định
    private boolean verifyCode(String input) {
        return input.equals("7X-K9-ACTF"); // ví dụ
    }
}