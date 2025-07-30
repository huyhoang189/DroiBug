package com.example.ctf_challenge_app.ctf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Lab12UserLeakFromLogFileActivity extends AppCompatActivity {

    private static final String TAG = "CTF_AuthLog";
    private static final String FILENAME = "authentication_log.txt";
    private static final String EXPECTED = "admin:123456";

    private EditText usernameEdt, passwordEdt;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab12_user_leak_from_log_file);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        saveAuthWithoutLog(EXPECTED);

        usernameEdt = findViewById(R.id.username);
        passwordEdt = findViewById(R.id.password);
        resultText = findViewById(R.id.flag_text);

        Button loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(v -> {
            String username = usernameEdt.getText().toString().trim();
            String password = passwordEdt.getText().toString().trim();

            String combined = username + ":" + password;
            saveAuthLog(combined);

            if (combined.equals(EXPECTED)){
                resultText.setText(FlagManager.getFlag("lab12"));

            }else {
                resultText.setText("");
                Toast.makeText(this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
            }

        });

        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab12UserLeakFromLogFileActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_12");
            startActivity(intent);
        });
    }


    private void saveAuthLog (String combined) {

        String status = combined.equals(EXPECTED) ? "SUCCESS" : "FAILED";

        String encoded = Base64.encodeToString(combined.getBytes(), Base64.DEFAULT);

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        String logEntry = encoded.trim() + " - " + timestamp + " - " + status;

        File file = new File(getFilesDir(), FILENAME);

        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write((logEntry + "\n").getBytes());
            Log.d(TAG, file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAuthWithoutLog (String combined) {

        String status = combined.equals(EXPECTED) ? "SUCCESS" : "FAILED";

        String encoded = Base64.encodeToString(combined.getBytes(), Base64.DEFAULT);

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        String logEntry = encoded.trim() + " - " + timestamp + " - " + status;

        File file = new File(getFilesDir(), FILENAME);

        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write((logEntry + "\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}