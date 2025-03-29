package com.example.ctf_challenge_app.ctf;


import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Lab19AesEcbDecryptActivity extends AppCompatActivity {

    private static final String TAG = "Lab_19_Log";
    private static final String FILE_NAME = "encrypted_data.txt";


    private EditText inputEditText;
    private TextView resultText;
    private Button checkBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab19_aes_ecb_decrypt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputEditText = findViewById(R.id.decrypt_input);
        resultText = findViewById(R.id.result_text);
        checkBtn = findViewById(R.id.check_btn);

        createEncryptedDataFile();

        checkBtn.setOnClickListener(v -> {
            String userInput = inputEditText.getText().toString().trim();
            if (checkInputAgainstEncrypted(userInput)) {
                resultText.setText(FlagManager.getFlag("lab19"));
            } else {
                resultText.setText("Không khớp dữ liệu đã mã hoá!");
            }
        });
    }

    private void createEncryptedDataFile() {
        try {
            File dir = getExternalFilesDir(null);
            if (!dir.exists()) dir.mkdirs();

            File file = new File(dir, FILE_NAME);

            String encrypted = encryptEcb(MainActivity.ORIGINAL_STR, MainActivity.AES_ECB_KEY);

            FileWriter fw = new FileWriter(file, false); // false = ghi đè
            fw.write(encrypted);
            fw.close();

            Log.d(TAG, "Dữ liệu mã hoá đã được ghi đè tại: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkInputAgainstEncrypted(String input) {
        try {

            File file = new File(getExternalFilesDir(null), FILE_NAME);
            Log.d(TAG, "Checking the code…" + file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(file));
            String encrypted = br.readLine();
            br.close();

            if (encrypted != null) {
                String decrypted = decryptAesEcb(encrypted, MainActivity.AES_ECB_KEY);
                return decrypted.equals(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String encryptEcb(String plainText, String key) throws Exception {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }

    private String decryptAesEcb(String base64Ciphertext, String key) throws Exception {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] decrypted = cipher.doFinal(Base64.decode(base64Ciphertext, Base64.DEFAULT));
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}