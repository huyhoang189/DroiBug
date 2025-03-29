package com.example.ctf_challenge_app.ctf;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.common.FlagManager;

public class Lab8XssAttackActivity extends AppCompatActivity {



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab8_xss_attack);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText inputHtml = findViewById(R.id.input_edt);
        Button btnLoad = findViewById(R.id.push_btn);
        WebView webView = findViewById(R.id.webview);
        Button copySampleBtn = findViewById(R.id.btn_copy_sample);
        TextView flagText = findViewById(R.id.flag_text);

        copySampleBtn.setOnClickListener(v -> {
            String flag = "<script>CTF.showFlag()</script>";
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Sample", flag);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "✅ Sample đã được copy vào clipboard!", Toast.LENGTH_SHORT).show();
        });


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // ⚠️ Cho phép chạy JS


        btnLoad.setOnClickListener(v -> {
            String htmlContent = inputHtml.getText().toString();

            // Không lọc đầu vào! (Lỗi bảo mật)
            String fullHtml = "<html><body>" + htmlContent + "</body></html>";
            webView.loadDataWithBaseURL(null, fullHtml, "text/html", "utf-8", null);
        });


        // JavaScript Interface (tùy chọn)
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void showFlag() {
                runOnUiThread(() -> {
                        flagText.setText(FlagManager.getFlag("lab8"));
                    }
                );
            }

        }, "CTF");


    }
}