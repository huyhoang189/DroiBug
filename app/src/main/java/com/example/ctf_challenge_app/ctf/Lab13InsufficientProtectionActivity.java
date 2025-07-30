package com.example.ctf_challenge_app.ctf;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Lab13InsufficientProtectionActivity extends AppCompatActivity {

    // Dữ liệu mã hóa dạng decimal (ASCII codes)
    private static final String VISA_NUMBER = "49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49"; // "1111111111111111"
    private static final String VISA_EXPIRY = "49 49 47 49 49"; // "11/11"
    private static final String VISA_CVV = "49 49 49"; // "111"



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab13_insufficient_protection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText numberInput = findViewById(R.id.visa_number);
        EditText expiryInput = findViewById(R.id.visa_expiry);
        EditText cvvInput = findViewById(R.id.visa_cvv);
        Button checkBtn = findViewById(R.id.check_btn);
        TextView result = findViewById(R.id.result_text);


        expiryInput.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;
            private final int TOTAL_LENGTH = 5; // "MM/YY"

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting) return;
                isFormatting = true;

                String input = s.toString().replace("/", "");
                if (input.length() >= 2) {
                    String formatted = input.substring(0, 2);
                    if (input.length() > 2) {
                        formatted += "/" + input.substring(2, Math.min(4, input.length()));
                    }
                    expiryInput.setText(formatted);
                    expiryInput.setSelection(formatted.length()); // move cursor to end
                }

                isFormatting = false;
            }
        });

        numberInput.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting; // tránh vòng lặp vô hạn
            private int prevLength = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                prevLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting) return;

                isFormatting = true;

                String input = s.toString().replaceAll("\\s", ""); // xóa dấu cách cũ
                StringBuilder formatted = new StringBuilder();

                for (int i = 0; i < input.length(); i++) {
                    formatted.append(input.charAt(i));
                    if ((i + 1) % 4 == 0 && i + 1 < input.length()) {
                        formatted.append(" ");
                    }
                }

                numberInput.setText(formatted.toString());
                numberInput.setSelection(formatted.length());

                isFormatting = false;
            }
        });

        checkBtn.setOnClickListener(v -> {
            String inputNumber = numberInput.getText().toString().replaceAll("\\s", "");
            String inputExpiry = expiryInput.getText().toString().trim();
            String inputCvv = cvvInput.getText().toString().trim();

            String realNumber = decodeDecimal(VISA_NUMBER);
            String realExpiry = decodeDecimal(VISA_EXPIRY);
            String realCvv = decodeDecimal(VISA_CVV);

            if (inputNumber.equals(realNumber) &&
                    inputExpiry.equals(realExpiry) &&
                    inputCvv.equals(realCvv)) {

                result.setText(FlagManager.getFlag("lab13"));
            } else {
                result.setText("Sai thông tin thẻ visa");
            }
        });

        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab13InsufficientProtectionActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_13");
            startActivity(intent);
        });
    }

    // Hàm giải mã từ decimal về chuỗi
    private String decodeDecimal(String encoded) {
        StringBuilder result = new StringBuilder();
        String[] parts = encoded.split(" ");
        for (String numStr : parts) {
            int ascii = Integer.parseInt(numStr);
            result.append((char) ascii);
        }
        return result.toString();
    }
}