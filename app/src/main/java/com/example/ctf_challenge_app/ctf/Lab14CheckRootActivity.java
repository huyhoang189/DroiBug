package com.example.ctf_challenge_app.ctf;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;

import java.io.File;

public class Lab14CheckRootActivity extends AppCompatActivity {

    private Button checkRootBtn;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab14_check_root);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ View
        checkRootBtn = findViewById(R.id.check_root_btn);
        resultText = findViewById(R.id.result_text);

        checkRootBtn.setOnClickListener(v -> {

            if (isDeviceRooted()) {
                resultText.setText("Thiết bị đã root. \n FLAG: " + FlagManager.getFlag("lab14"));
            } else {
                resultText.setText("Thiết bị chưa root. Truy cập bị từ chối.");
            }
        });
    }

    // Hàm kiểm tra root cơ bản
    private boolean isDeviceRooted() {
        String[] paths = {
                "/system/xbin/su",
                "/system/bin/su",
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/sd/xbin/su"
        };
        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return true;
    }
}