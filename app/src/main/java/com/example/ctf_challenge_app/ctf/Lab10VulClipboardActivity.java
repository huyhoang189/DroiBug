package com.example.ctf_challenge_app.ctf;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.HelpActivity;
import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Lab10VulClipboardActivity extends AppCompatActivity {

    private static final String SECRET_CODE = "0x1132c4!";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab10_vul_clipboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


         EditText itemInput =  findViewById(R.id.clip_board_edt);
         TextView flagText = findViewById(R.id.flag_text);
        Button submitBtn = findViewById(R.id.submit_btn);

        // 📋 1. Ghi dữ liệu nhạy cảm vào clipboard khi mở Activity
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("secret", SECRET_CODE);
        clipboard.setPrimaryClip(clip);

        submitBtn.setOnClickListener(v -> {
            String userInput = itemInput.getText().toString().trim();
            if (userInput.equals(SECRET_CODE)) {
                flagText.setText(FlagManager.getFlag("lab10"));
            } else {
                flagText.setText("❌ Không đúng. Hãy kiểm tra clipboard kỹ hơn!");
            }
        });

        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab10VulClipboardActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_10");
            startActivity(intent);
        });
    }
}