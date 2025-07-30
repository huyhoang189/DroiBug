package com.example.ctf_challenge_app.ctf;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.HelpActivity;
import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Lab18JwtTokenCheckActivity extends AppCompatActivity {



    private static final String EXPECTED_USER_ID = "ctf_user_2025";
    private static final String JWT_FILE_NAME = "access_token.jwt";

    EditText inputUserId;
    TextView resultText;
    Button checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab18_jwt_token_check);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Xin quyền ghi nếu cần (chỉ cho Android < 11)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
        }

        inputUserId = findViewById(R.id.user_id_input);
        resultText = findViewById(R.id.result_text);
        checkBtn = findViewById(R.id.check_btn);

        createJWTFile();

        checkBtn.setOnClickListener(v -> {
            String input = inputUserId.getText().toString().trim();
            if (EXPECTED_USER_ID.equals(input)) {
                resultText.setText(FlagManager.getFlag("lab18"));
            } else {
                resultText.setText("Mã người dùng không đúng!");
            }
        });

        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab18JwtTokenCheckActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_18");
            startActivity(intent);
        });
    }

    private void createJWTFile() {
        try {
            File dir = getExternalFilesDir(null); // /sdcard/Android/data/<package>/files/
            if (!dir.exists()) dir.mkdirs();

            File file = new File(dir, JWT_FILE_NAME);
            if (file.exists()) return; // không ghi đè

            String header = Base64.encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes(), Base64.NO_WRAP);
            String payload = Base64.encodeToString(("{\"user_id\":\"" + EXPECTED_USER_ID + "\"}").getBytes(), Base64.NO_WRAP);
            String signature = "fakesignature123"; // giả lập

            String jwt = header + "." + payload + "." + signature;

            FileWriter fw = new FileWriter(file);
            fw.write(jwt);
            fw.close();

            Log.d("CTF", "Đã ghi file JWT tại: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}