package com.example.ctf_challenge_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.adapters.ChallengeAdapter;
import com.example.ctf_challenge_app.databases.LabDAO;
import com.example.ctf_challenge_app.models.Challenge;
import com.example.ctf_challenge_app.models.ChallengeMapper;
import com.example.ctf_challenge_app.utils.JsonFileReader;
import com.example.ctf_challenge_app.utils.JsonParser;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    public static final String AES_ECB_KEY = "6374665f666c6167";
    public static final String ORIGINAL_STR = "s3cr3tp@ss";

    ListView challengeListView;
    ChallengeAdapter challengeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        challengeListView = findViewById(R.id.listChallenge);
        TextView toolbar_txt = findViewById(R.id.toolbar);
        toolbar_txt.setText("Challenges");
        initState(MainActivity.this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String jsonData = JsonFileReader.readJsonFromFile(MainActivity.this, "challenges.json");


        JsonParser parse = new JsonParser();
        ChallengeMapper mapper = new ChallengeMapper();

        ArrayList<Challenge> challenges = (ArrayList<Challenge>) parse.parseArray(jsonData, mapper);

        challengeAdapter = new ChallengeAdapter(MainActivity.this, R.layout.challenge_item, challenges);

        challengeListView.setAdapter(challengeAdapter);

        //Handle click
        challengeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(MainActivity.this, ListPractice.class);
                myIntent.putExtra("id", challenges.get(position).getId());
                myIntent.putExtra("title", challenges.get(position).getTitle());
                startActivity(myIntent);
            }
        });
    }


    private void initState(Context ctx){
        initLabs(ctx);
    }

    private void initLabs(Context ctx){
        LabDAO labDAO = new LabDAO(ctx);

        // Improper Credential Usage
        labDAO.insertLab("lab_1", "Tìm mật khẩu hardcoded trong mã nguồn bằng JADX", "Phân tích mã nguồn để tìm mật khẩu hardcoded.", 0, "improper_credential_usage");
        labDAO.insertLab("lab_2", "Giải mã token API được lưu trữ trong SharedPreferences không mã hóa", "Phát hiện API key bị lộ thông qua SharedPreferences.", 0, "improper_credential_usage");
        labDAO.insertLab("lab_3", "Trích xuất thông tin đăng nhập từ file log của ứng dụng", "Kiểm tra file log để lấy thông tin đăng nhập.", 0, "improper_credential_usage");

// Insecure Authentication/Authorization
        labDAO.insertLab("lab_4", "Bypass màn hình đăng nhập bằng cách chỉnh sửa Smali", "Chỉnh sửa mã Smali để bỏ qua xác thực.", 0, "insecure_authentication");
        labDAO.insertLab("lab_5", "Truy cập tài khoản admin bằng cách sửa đổi SharedPreferences", "Sửa dữ liệu SharedPreferences để truy cập tài khoản admin.", 0, "insecure_authentication");
        labDAO.insertLab("lab_6", "Dùng Frida để bỏ qua kiểm tra token xác thực", "Sử dụng Frida để phân tích và bỏ qua xác thực token.", 0, "insecure_authentication");

// Insufficient Input/Output Validation
        labDAO.insertLab("lab_7", "Nhập ký tự đặc biệt để phá vỡ kiểm tra đầu vào của ứng dụng", "Kiểm tra validation bằng cách nhập ký tự đặc biệt.", 0, "insufficient_input_validation");
        labDAO.insertLab("lab_8", "Lợi dụng WebView để thực thi JavaScript độc hại", "Tấn công WebView bằng JavaScript độc hại.", 0, "insufficient_input_validation");
        labDAO.insertLab("lab_9", "Gửi input đặc biệt để truy xuất dữ liệu ngoài phạm vi cho phép", "Thử nghiệm truy xuất dữ liệu ngoài giới hạn.", 0, "insufficient_input_validation");

// Inadequate Privacy Controls
        labDAO.insertLab("lab_10", "Đọc dữ liệu cá nhân được lưu trong clipboard của ứng dụng", "Kiểm tra khả năng truy cập dữ liệu từ clipboard.", 0, "inadequate_privacy_controls");
        labDAO.insertLab("lab_11", "Trích xuất danh bạ bị lộ do ứng dụng cấp quyền sai cách", "Kiểm tra quyền truy cập danh bạ.", 0, "inadequate_privacy_controls");
        labDAO.insertLab("lab_12", "Lấy thông tin của người dùng từ file log hệ thống", "Tìm kiếm thông tin nhạy cảm trong file log hệ thống.", 0, "inadequate_privacy_controls");

// Insufficient Binary Protections
        labDAO.insertLab("lab_13", "Decompile ứng dụng để tìm flag ẩn trong mã nguồn", "Dịch ngược ứng dụng để tìm flag.", 0, "insufficient_binary_protection");
        labDAO.insertLab("lab_14", "Bypass kiểm tra root bằng cách sửa đổi Smali", "Chỉnh sửa mã Smali để bỏ qua kiểm tra root.", 0, "insufficient_binary_protection");
        labDAO.insertLab("lab_15", "Hook một hàm quan trọng bằng Frida để thay đổi kết quả", "Dùng Frida để thay đổi kết quả của một hàm quan trọng.", 0, "insufficient_binary_protection");

// Insecure Data Storage
        labDAO.insertLab("lab_16", "Lấy thông tin đăng nhập từ SharedPreferences không mã hóa", "Kiểm tra dữ liệu trong SharedPreferences.", 0, "insecure_data_storage");
        labDAO.insertLab("lab_17", "Đọc dữ liệu SQLite database của ứng dụng bằng ADB", "Truy xuất dữ liệu trong database SQLite qua ADB.", 0, "insecure_data_storage");
        labDAO.insertLab("lab_18", "Trích xuất dữ liệu nhạy cảm từ file trên bộ nhớ thiết bị", "Kiểm tra dữ liệu nhạy cảm trong bộ nhớ trong của thiết bị.", 0, "insecure_data_storage");

// Insufficient Cryptography
        labDAO.insertLab("lab_19", "Giải mã dữ liệu mã hóa bằng AES ECB mode", "Kiểm tra khả năng giải mã AES ECB mode.", 0, "insufficient_cryptography");
        labDAO.insertLab("lab_20", "Tìm khóa mã hóa hardcoded trong mã nguồn ứng dụng", "Tìm kiếm khóa mã hóa được lưu trong mã nguồn.", 0, "insufficient_cryptography");
        labDAO.insertLab("lab_21", "Bypass kiểm tra mã hóa bằng cách sửa đổi ứng dụng", "Chỉnh sửa ứng dụng để bỏ qua kiểm tra mã hóa.", 0, "insufficient_cryptography");





    }


}