// Lab 20 - Hardcoded AES Key Decryption - nhập key để giải mã ciphertext

package com.example.ctf_challenge_app.ctf;

import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.MainActivity;
import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Lab20HardcodedKeyActivity extends AppCompatActivity {

    private static final String ENCRYPTED_PIN = "q78+VUdpIacRiGWKZifwew=="; // ciphertext của "654321"
    private static final String SECRET_KEY = "ThisIsAesKey1234"; // hardcoded key (16 bytes)

    EditText phoneInput, pinInput;
    TextView resultText;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab20_hardcoded_key);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pinInput = findViewById(R.id.pin_input);
        resultText = findViewById(R.id.result_text);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(v -> {
            String pin = pinInput.getText().toString().trim();

            try {
                String encryptedPin = encrypt(pin, SECRET_KEY);
                if (encryptedPin.equals(ENCRYPTED_PIN)) {
                    resultText.setText(FlagManager.getFlag("lab20"));
                } else {
                    resultText.setText("Mã PIN sai. Hãy thử lại!");
                }
            } catch (Exception e) {
                resultText.setText("Lỗi mã hoá: " + e.getMessage());
            }
        });
    }

    private String encrypt(String plainText, String key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }
}
