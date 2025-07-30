package com.example.ctf_challenge_app.ctf;

import android.content.Intent;
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

import com.example.ctf_challenge_app.HelpActivity;
import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Lab4ByPassSmaliActivity extends AppCompatActivity {

    private final String REAL_USER = "admin";
    private final String REAL_PASS = "password";

    private final boolean IS_LOGIN = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab4_by_pass_smali);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText usernameEt = findViewById(R.id.username);
        EditText passwordEt = findViewById(R.id.password);
        Button loginBtn = findViewById(R.id.login_btn);
        TextView flagText = findViewById(R.id.flag_text);

        loginBtn.setOnClickListener(v -> {
            String inputUser = usernameEt.getText().toString();
            String inputPass = passwordEt.getText().toString();

            if (inputUser.equals(REAL_USER) && inputPass.equals(REAL_PASS) && IS_LOGIN) {
                flagText.setText(FlagManager.getFlag("lab4"));
            } else {
                flagText.setText("");
                Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab4ByPassSmaliActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_4");
            startActivity(intent);
        });
    }
}