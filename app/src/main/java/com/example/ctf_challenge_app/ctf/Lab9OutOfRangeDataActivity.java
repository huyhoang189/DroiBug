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

import java.util.Random;

public class Lab9OutOfRangeDataActivity extends AppCompatActivity {

    private static final int MAX_ID = 1000;


    private final String[] colors = {"Đỏ", "Xanh", "Vàng", "Đen", "Trắng"};
    private final String[] materials = {"Nhựa", "Kim loại", "Gỗ", "Vải", "Thủy tinh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab9_out_of_range_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView flagText = findViewById(R.id.flag_text);
        EditText itemIndexInput = findViewById(R.id.item_index);
        Button searchBtn = findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(v -> {
            String input = itemIndexInput.getText().toString().trim();
            try {
                int id = Integer.parseInt(input);

                if (id == Integer.MIN_VALUE || id == Integer.MAX_VALUE) {
                    flagText.setText(FlagManager.getFlag("lab9"));
                } else if (id < 0 || id >= MAX_ID) {
                    flagText.setText("Không tồn tại sản phẩm với ID: " + id);
                } else {
                    String name = "Sản phẩm #" + id;
                    String color = colors[new Random().nextInt(colors.length)];
                    String material = materials[new Random().nextInt(materials.length)];

                    String result = "✔️ Tên: " + name + "\n"
                            + "🎨 Màu sắc: " + color + "\n"
                            + "🧱 Chất liệu: " + material;

                    flagText.setText(result);
                }
            } catch (NumberFormatException e) {
                flagText.setText("Dữ liệu không hợp lệ. Vui lòng nhập số.");
            }

        });


        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab9OutOfRangeDataActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_9");
            startActivity(intent);
        });
    }

}