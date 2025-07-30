package com.example.ctf_challenge_app.ctf;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
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

public class Lab11ContactsLeakActivity extends AppCompatActivity {


    private static final int REQUEST_CONTACTS = 100;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab11_contacts_leak);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.leak_contact_btn);
        resultText = findViewById(R.id.flag_text);

        btn.setOnClickListener(v -> checkAndReadContacts());


        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab11ContactsLeakActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_11");
            startActivity(intent);
        });

    }

    private void checkAndReadContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CONTACTS);
        } else {
            // Đã có quyền → kiểm tra danh bạ và hiển thị FLAG nếu truy xuất được
            Cursor cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null
            );

            if (cursor != null && cursor.moveToFirst()) {
                // Có ít nhất một contact → hiển thị FLAG
                resultText.setText(FlagManager.getFlag("lab11"));

                cursor.close();
            } else {
                resultText.setText("Không tìm thấy dữ liệu danh bạ.");
            }
        }
    }

    // Nhận kết quả sau khi người dùng cấp quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Gọi lại hành động sau khi đã được cấp quyền
                checkAndReadContacts();
            } else {
                resultText.setText("Không có quyền đọc danh bạ.");
            }
        }
    }
}