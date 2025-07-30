package com.example.ctf_challenge_app.ctf;

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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Lab21BypassCryptoCheckActivity extends AppCompatActivity {


    private static final String HASHED_VALID_CODE = "24eed33ee38027d124839f0fcdf6adcb"; // md5("activation")

    EditText codeInput;
    TextView flagText;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab21_bypass_crypto_check);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        codeInput = findViewById(R.id.activation_code_input);
        flagText = findViewById(R.id.flag_text);
        submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(v -> {
            String userInput = codeInput.getText().toString().trim();
            String hashedInput = md5(userInput);

            if (checkCorrectCode(hashedInput)) {
                flagText.setText(FlagManager.getFlag("lab21"));
            } else {
                flagText.setText("Mã kích hoạt không hợp lệ!");
            }
        });

        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab21BypassCryptoCheckActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_21");
            startActivity(intent);
        });


    }

    private boolean checkCorrectCode (String str){
        return str.equals(HASHED_VALID_CODE);
    }

    private String md5(String input) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}