package com.example.ctf_challenge_app;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HelpActivity extends AppCompatActivity {

    private TextView helpTitle;
    private WebView helpContent;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_help);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        helpTitle = findViewById(R.id.help_title);
        helpContent = findViewById(R.id.help_content);
        backButton = findViewById(R.id.back_button);

        String labName = getIntent().getStringExtra("LAB_CODE");
        if (labName == null) labName = "unknown";

        helpTitle.setText(getLabTitle(labName));
        String html = getHelpContent(labName);

        helpContent.getSettings().setJavaScriptEnabled(true);
        helpContent.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);

        backButton.setOnClickListener(v -> finish());
    }

    private String getLabTitle(String labName) {
        switch (labName) {
            case "lab_1": return "Lab 1 - Mật khẩu Hardcode";
            case "lab_2": return "Lab 2 - Token API trong SharedPreferences";
            case "lab_3": return "Lab 3 - Rò rỉ thông tin đăng nhập qua Logcat";
            case "lab_4": return "Lab 4 - Vượt qua đăng nhập bằng Smali";
            case "lab_5": return "Lab 5 - Sửa SharedPreferences (Admin)";
            case "lab_6": return "Lab 6 - Bỏ qua Token bằng Frida";
            case "lab_7": return "Lab 7 - Tấn công đầu vào";
            case "lab_8": return "Lab 8 - Tấn công XSS qua WebView";
            case "lab_9": return "Lab 9 - Khai thác vượt phạm vi đầu vào";
            case "lab_10": return "Lab 10 - Rò rỉ dữ liệu Clipboard";
            case "lab_11": return "Lab 11 - Khai thác quyền truy cập danh bạ";
            case "lab_12": return "Lab 12 - Thông tin xác thực trong Log";
            case "lab_13": return "Lab 13 - Flag ẩn trong mã nguồn";
            case "lab_14": return "Lab 14 - Vượt qua kiểm tra Root";
            case "lab_15": return "Lab 15 - Hook verifyCode bằng Frida";
            case "lab_16": return "Lab 16 - SharedPreferences không mã hóa";
            case "lab_17": return "Lab 17 - Cơ sở dữ liệu SQLite không mã hóa";
            case "lab_18": return "Lab 18 - Token JWT trong bộ nhớ ngoài";
            case "lab_19": return "Lab 19 - Giải mã AES-ECB";
            case "lab_20": return "Lab 20 - Khóa mã hóa Hardcode";
            case "lab_21": return "Lab 21 - Bỏ qua kiểm tra mã hóa";
            default: return "Bài lab không xác định";
        }
    }

    private String getHelpContent(String labName) {
        String css = "<style>" +
                "body { font-family: Arial, sans-serif; padding: 10px; font-size : 10px }" +
                "h1 { color: #2c3e50; }" +
                "h2 { color: #34495e; }" +
                "p, li { line-height: 1.6; }" +
                "ul { margin-left: 10px; }" +
                "</style>";

        switch (labName) {
            case "lab_1":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 1: Mật khẩu Hardcode</h1>" +
                        "<h2>Mục tiêu</h2><p>Trích xuất mật khẩu hardcode trong mã nguồn để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Mật khẩu được hardcode trực tiếp trong mã nguồn, dễ bị truy cập qua dịch ngược APK.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Mở ứng dụng, vào Lab 1 để quan sát.</li>" +
                        "<li>Dùng JADX-GUI tìm mật khẩu trong <code>Lab1HardCorePasswordActivity</code>.</li>" +
                        "<li>Nhập mật khẩu vào ô textbox.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX-GUI</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Cấu hình đúng JADX.</li><li>Kiểm tra mật khẩu trước khi nhập.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Hardcode_Is_Bad}</p>" +
                        "</body></html>";

            case "lab_2":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 2: Token API trong SharedPreferences</h1>" +
                        "<h2>Mục tiêu</h2><p>Trích xuất và giải mã token API để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Token API được lưu không mã hóa trong SharedPreferences, dễ bị truy cập.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Mở ứng dụng, vào Lab 2, nhấn 'Tạo Token API'.</li>" +
                        "<li>Dùng JADX tìm token trong <code>app_data.xml</code>.</li>" +
                        "<li>Dùng <code>adb shell</code> trích xuất token.</li>" +
                        "<li>Giải mã token (Base64) để được <code>s3cr3t_k3y_f0r</code>.</li>" +
                        "<li>Nhập chuỗi vào ứng dụng.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX</li><li>ADB</li><li>Công cụ giải mã Base64</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Cần quyền root hoặc trình giả lập.</li><li>Kiểm tra định dạng token.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{SharedPrefs_are_not_secure_storage}</p>" +
                        "</body></html>";

            case "lab_3":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 3: Rò rỉ thông tin đăng nhập qua Logcat</h1>" +
                        "<h2>Mục tiêu</h2><p>Trích xuất thông tin đăng nhập từ log để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Ứng dụng ghi thông tin nhạy cảm vào log, có thể truy cập qua logcat.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Mở ứng dụng, vào Lab 3.</li>" +
                        "<li>Dùng <code>adb logcat</code> theo dõi log.</li>" +
                        "<li>Nhấn 'Tạo Log', tìm tài khoản/mật khẩu.</li>" +
                        "<li>Đăng nhập bằng thông tin trích xuất.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>ADB</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Dùng <code>adb logcat | grep &lt;từ khóa&gt;</code> để lọc.</li><li>Không bỏ sót thông tin.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Hard_code_logs_expose_secrets}</p>" +
                        "</body></html>";

            case "lab_4":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 4: Vượt qua đăng nhập bằng Smali</h1>" +
                        "<h2>Mục tiêu</h2><p>Sửa mã Smali để bypass đăng nhập và lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Biến <code>IS_LOGIN</code> kiểm soát đăng nhập, có thể chỉnh sửa qua Smali.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng <code>apktool</code> dịch ngược APK.</li>" +
                        "<li>Tìm Activity chứa <code>IS_LOGIN</code>.</li>" +
                        "<li>Sửa Smali để <code>IS_LOGIN=true</code>.</li>" +
                        "<li>Build lại APK.</li>" +
                        "<li>Ký APK bằng <code>keytool</code>.</li>" +
                        "<li>Cài đặt và kiểm tra.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>APKTool</li><li>Keytool</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Ký APK đúng.</li><li>Kiểm tra logic <code>IS_LOGIN</code>.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Smali_By_Pass_Successful}</p>" +
                        "</body></html>";

            case "lab_5":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 5: Sửa SharedPreferences (Admin)</h1>" +
                        "<h2>Mục tiêu</h2><p>Sửa SharedPreferences để truy cập quyền admin và lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Trạng thái <code>is_admin</code> trong SharedPreferences có thể chỉnh sửa.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng JADX tìm <code>is_admin</code> trong SharedPreferences.</li>" +
                        "<li>Dùng <code>adb shell</code> sửa <code>is_admin</code> thành <code>true</code>.</li>" +
                        "<li>Chạy lại ứng dụng, kiểm tra quyền admin.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX</li><li>ADB</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Cần quyền root hoặc trình giả lập.</li><li>Kiểm tra file XML sau sửa.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{SharedPrefs_CanBeHacked}</p>" +
                        "</body></html>";

            case "lab_6":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 6: Bỏ qua Token bằng Frida</h1>" +
                        "<h2>Mục tiêu</h2><p>Dùng Frida bypass kiểm tra token để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Hàm <code>checkToken</code> có thể sửa để luôn trả về <code>true</code>.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng JADX phân tích, xác định <code>getAuthToken</code> và <code>checkToken</code>.</li>" +
                        "<li>Tạo script Frida (<code>lab6_bypass.js</code>) sửa <code>checkToken</code>.</li>" +
                        "<li>Gắn script vào ứng dụng.</li>" +
                        "<li>Chạy lại ứng dụng và kiểm tra.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX</li><li>Frida</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Cấu hình Frida đúng.</li><li>Kiểm tra tên hàm chính xác.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Frida_Bypass_Token_OK}</p>" +
                        "</body></html>";

            case "lab_7":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 7: Tấn công đầu vào</h1>" +
                        "<h2>Mục tiêu</h2><p>Nhập ký tự đặc biệt để bypass kiểm tra và lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Ứng dụng không kiểm tra đầy đủ đầu vào, cho phép bypass cơ chế bảo vệ.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Mở giao diện Lab 7.</li>" +
                        "<li>Thử nhập ký tự đặc biệt (e.g., <code>' or 1=1--</code>).</li>" +
                        "<li>Kiểm tra flag hiển thị.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Thử nhiều tổ hợp ký tự.</li><li>Ghi chú chuỗi thành công.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Input_Validation_Bypassed}</p>" +
                        "</body></html>";

            case "lab_8":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 8: Tấn công XSS qua WebView</h1>" +
                        "<h2>Mục tiêu</h2><p>Chèn JavaScript vào WebView để kích hoạt flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>WebView cho phép thực thi JavaScript, có thể gọi <code>showFlag()</code>.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Mở giao diện Lab 8.</li>" +
                        "<li>Chèn <code>&lt;script&gt;CTF.showFlag()&lt;/script&gt;</code>.</li>" +
                        "<li>Kiểm tra flag hiển thị.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Chèn mã JavaScript chính xác.</li><li>Kiểm tra phản hồi WebView.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{WebView_XSS_Executed}</p>" +
                        "</body></html>";

            case "lab_9":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 9: Khai thác vượt phạm vi đầu vào</h1>" +
                        "<h2>Mục tiêu</h2><p>Nhập dữ liệu đặc biệt để truy cập thông tin ngoài phạm vi.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Ứng dụng không kiểm tra đúng giới hạn đầu vào kiểu <code>int</code>.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Mở giao diện Lab 9.</li>" +
                        "<li>Nhập giá trị <code>int</code> lớn (e.g., <code>Integer.MAX_VALUE</code>).</li>" +
                        "<li>Kiểm tra phản hồi và tìm flag.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Thử giá trị biên.</li><li>Quan sát phản hồi ứng dụng.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Accessed_Restricted_IntBoundary}</p>" +
                        "</body></html>";

            case "lab_10":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 10: Rò rỉ dữ liệu Clipboard</h1>" +
                        "<h2>Mục tiêu</h2><p>Đọc dữ liệu nhạy cảm từ clipboard để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Ứng dụng lưu dữ liệu trong clipboard, có thể truy cập bằng <code>ClipboardManager</code>.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Viết script Android gọi <code>ClipboardManager.getPrimaryClip()</code>.</li>" +
                        "<li>Trích xuất dữ liệu: <code>0x1132c4!</code>.</li>" +
                        "<li>Nhập dữ liệu vào ứng dụng.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>Android Studio/script Android</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Đảm bảo script truy cập đúng clipboard.</li><li>Kiểm tra dữ liệu trước nhập.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Clipboard_Exposure_Success}</p>" +
                        "</body></html>";

            case "lab_11":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 11: Khai thác quyền truy cập danh bạ</h1>" +
                        "<h2>Mục tiêu</h2><p>Khai thác quyền truy cập danh bạ sai cách để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Ứng dụng yêu cầu quyền danh bạ, hiển thị flag nếu cấp quyền.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Mở giao diện Lab 11, cấp quyền danh bạ.</li>" +
                        "<li>Kiểm tra flag hiển thị.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Cấp quyền trong popup.</li><li>Kiểm tra lại nếu flag không hiển thị.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Contacts_Leaked_Via_App}</p>" +
                        "</body></html>";

            case "lab_12":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 12: Thông tin xác thực trong Log</h1>" +
                        "<h2>Mục tiêu</h2><p>Trích xuất thông tin đăng nhập từ log hệ thống.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Ứng dụng ghi thông tin đăng nhập vào log, có thể giải mã Base64.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Mở Lab 12, thử đăng nhập để sinh log.</li>" +
                        "<li>Dùng <code>adb logcat | grep CTF_AuthLog</code>.</li>" +
                        "<li>Truy cập <code>authentication_log.txt</code>, giải mã Base64: <code>admin/123456</code>.</li>" +
                        "<li>Đăng nhập bằng thông tin.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>ADB</li><li>Công cụ giải mã Base64</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Lọc log cẩn thận.</li><li>Kiểm tra định dạng Base64.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Encrypted_Log_Extraction_Success}</p>" +
                        "</body></html>";

            case "lab_13":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 13: Flag ẩn trong mã nguồn</h1>" +
                        "<h2>Mục tiêu</h2><p>Dịch ngược APK để tìm flag ẩn.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Flag được lưu dưới dạng ASCII trong mã nguồn.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng JADX tìm trong <code>Lab13InsufficientProtectionActivity</code>.</li>" +
                        "<li>Chuyển ASCII <code>49 49 49</code> thành <code>111</code>.</li>" +
                        "<li>Nhập chuỗi vào ứng dụng.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Dịch ngược chính xác lớp.</li><li>Kiểm tra chuỗi ASCII.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Visa_Info_Reverse_Success}</p>" +
                        "</body></html>";

            case "lab_14":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 14: Vượt qua kiểm tra Root</h1>" +
                        "<h2>Mục tiêu</h2><p>Sửa mã Smali để bypass kiểm tra root.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Hàm <code>isDeviceRooted()</code> có thể sửa để trả về <code>true</code>.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng <code>apktool</code> dịch ngược APK.</li>" +
                        "<li>Sửa <code>isDeviceRooted()</code> trong <code>Lab14CheckRootActivity</code>.</li>" +
                        "<li>Build và ký lại APK.</li>" +
                        "<li>Cài đặt và kiểm tra.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>APKTool</li><li>Keytool</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Kiểm tra logic hàm.</li><li>Ký APK đúng.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Root_Blocked_Logic_Bypassed}</p>" +
                        "</body></html>";

            case "lab_15":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 15: Hook verifyCode bằng Frida</h1>" +
                        "<h2>Mục tiêu</h2><p>Hook hàm <code>verifyCode</code> để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Hàm <code>verifyCode</code> trong <code>Lab15HookFridaVerifyActivity</code> có thể hook.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng JADX xác định <code>verifyCode</code>.</li>" +
                        "<li>Tạo script Frida hook trả về <code>true</code>.</li>" +
                        "<li>Gắn script vào ứng dụng.</li>" +
                        "<li>Chạy lại ứng dụng và kiểm tra.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX</li><li>Frida</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Cấu hình Frida đúng.</li><li>Kiểm tra tên hàm.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Hooked_And_Won}</p>" +
                        "</body></html>";

            case "lab_16":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 16: SharedPreferences không mã hóa</h1>" +
                        "<h2>Mục tiêu</h2><p>Trích xuất thông tin đăng nhập không mã hóa để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Thông tin đăng nhập lưu trong <code>user_data.xml</code> không mã hóa.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng JADX tìm thông tin: <code>admin/admin123</code>.</li>" +
                        "<li>Nhập thông tin vào ứng dụng.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Kiểm tra đường dẫn file.</li><li>Xác minh thông tin trước nhập.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{SharedPrefs_Not_Safe}</p>" +
                        "</body></html>";

            case "lab_17":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 17: Cơ sở dữ liệu SQLite không mã hóa</h1>" +
                        "<h2>Mục tiêu</h2><p>Trích xuất thông tin đăng nhập từ SQLite để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Thông tin đăng nhập lưu trong <code>userinfo.db</code> không mã hóa.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng <code>adb shell</code> truy cập <code>userinfo.db</code>.</li>" +
                        "<li>Trích xuất database ra máy tính.</li>" +
                        "<li>Đọc dữ liệu từ database.</li>" +
                        "<li>Nhập thông tin vào ứng dụng.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>ADB</li><li>SQLite viewer</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Cần quyền root hoặc trình giả lập.</li><li>Kiểm tra cấu trúc database.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Extracted_From_SQLite}</p>" +
                        "</body></html>";

            case "lab_18":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 18: Token JWT trong bộ nhớ ngoài</h1>" +
                        "<h2>Mục tiêu</h2><p>Trích xuất và giải mã JWT token để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>JWT token lưu trên bộ nhớ ngoài, có thể truy cập và giải mã.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng <code>adb shell</code> tìm <code>access_token.jwt</code>.</li>" +
                        "<li>Giải mã token: <code>{\"user_id\":\"ctf_user_2025\"}</code>.</li>" +
                        "<li>Nhập <code>user_id</code> vào ứng dụng.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>ADB</li><li>Công cụ giải mã JWT</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Kiểm tra đường dẫn file.</li><li>Xác minh định dạng token.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{JWT_Extracted_From_Public_Storage}</p>" +
                        "</body></html>";

            case "lab_19":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 19: Giải mã AES-ECB</h1>" +
                        "<h2>Mục tiêu</h2><p>Giải mã dữ liệu AES-ECB để lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Dữ liệu mã hóa bằng AES-ECB với khóa hardcode, dễ trích xuất.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng <code>adb logcat</code> bắt chuỗi: <code>JXqJL74h5kTgq544JyT5RA==</code>.</li>" +
                        "<li>Dùng JADX tìm khóa AES.</li>" +
                        "<li>Giải mã để được <code>s3cr3tp@ss</code>.</li>" +
                        "<li>Nhập chuỗi vào ứng dụng.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>ADB</li><li>JADX</li><li>Công cụ giải mã AES</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Xác minh khóa và chuỗi.</li><li>Dùng đúng chế độ AES-ECB.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Encrypted_ECB_Stored_Successfully}</p>" +
                        "</body></html>";

            case "lab_20":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 20: Khóa mã hóa Hardcode</h1>" +
                        "<h2>Mục tiêu</h2><p>Trích xuất khóa hardcode để giải mã PIN và lấy flag.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Khóa mã hóa được hardcode, cho phép giải mã PIN.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng JADX tìm chuỗi và khóa mã hóa.</li>" +
                        "<li>Giải mã để được PIN: <code>654321</code>.</li>" +
                        "<li>Nhập PIN vào ứng dụng.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX</li><li>Công cụ giải mã</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Kiểm tra chuỗi và khóa.</li><li>Xác minh định dạng PIN.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{PIN_With_Hardcoded_Key}</p>" +
                        "</body></html>";

            case "lab_21":
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Lab 21: Bỏ qua kiểm tra mã hóa</h1>" +
                        "<h2>Mục tiêu</h2><p>Sửa mã nguồn để bypass kiểm tra mã hóa.</p>" +
                        "<h2>Mô tả lỗ hổng</h2><p>Hàm <code>checkCorrectCode</code> có thể sửa để luôn trả về <code>true</code>.</p>" +
                        "<h2>Các bước thực hiện</h2><ul>" +
                        "<li>Dùng JADX xác định <code>checkCorrectCode</code>.</li>" +
                        "<li>Dùng <code>apktool</code> sửa hàm trả về <code>true</code>.</li>" +
                        "<li>Build và ký lại APK.</li>" +
                        "<li>Cài đặt và kiểm tra.</li>" +
                        "<li>Chụp ảnh màn hình khi lấy flag.</li></ul>" +
                        "<h2>Công cụ cần thiết</h2><ul><li>JADX</li><li>APKTool</li><li>Keytool</li><li>Thiết bị Android/trình giả lập</li></ul>" +
                        "<h2>Lưu ý</h2><ul><li>Sửa đúng logic hàm.</li><li>Ký APK đúng.</li></ul>" +
                        "<h2>Kết quả</h2><p>Flag{Bypassed_Encrypted_Check}</p>" +
                        "</body></html>";

            default:
                return "<html><head>" + css + "</head><body>" +
                        "<h1>Bài lab không xác định</h1>" +
                        "<p>Vui lòng chọn LAB_NAME từ lab_1 đến lab_21.</p>" +
                        "</body></html>";
        }
    }
}