package com.example.ctf_challenge_app.ctf;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;

public class Lab7InputCheckActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab7_input_check);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText usernameEt = findViewById(R.id.username);
        Button loginBtn = findViewById(R.id.login_btn);
        TextView flagText = findViewById(R.id.flag_text);

        loginBtn.setOnClickListener(v -> {
            String input = usernameEt.getText().toString();


            if (input.contains("admin")) {
                flagText.setText("");
                Toast.makeText(this, "Tài khoản không đúng!", Toast.LENGTH_SHORT).show();
            } else if (input.matches("^[a-zA-Z0-9_]*$")) {
                Toast.makeText(this, "Chào " + input + "! Bạn là người dùng thường.", Toast.LENGTH_SHORT).show();
                flagText.setText("");
            } else {
                // Nếu có ký tự đặc biệt → coi như 'lỗi' kiểm tra → hiển thị FLAG (lỗ hổng!)
                flagText.setText(FlagManager.getFlag("lab7"));
            }


        });





    }
}