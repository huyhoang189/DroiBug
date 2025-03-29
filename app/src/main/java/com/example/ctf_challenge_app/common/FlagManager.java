package com.example.ctf_challenge_app.common;


import java.util.HashMap;
import java.util.Map;

public class FlagManager {
    private static final Map<String, String> labFlags = new HashMap<>();

    static {
        // Thêm tên lab và flag tương ứng tại đây
        labFlags.put("lab1", "Flag{Hardcode_Is_Bad}");
        labFlags.put("lab2", "Flag{SharedPrefs_are_not_secure_storage}");
        labFlags.put("lab3", "Flag{Hard_code_logs_expose_secrets}");
        labFlags.put("lab4", "Flag{Smali_By_Pass_Successful}");
        labFlags.put("lab5", "Flag{SharedPrefs_CanBeHacked}");
        labFlags.put("lab6", "Flag{Frida_Bypass_Token_OK}");
        labFlags.put("lab7", "Flag{Input_Validation_Bypassed}");
        labFlags.put("lab8", "Flag{WebView_XSS_Executed}");
        labFlags.put("lab9", "Flag{Accessed_Restricted_IntBoundary}");
        labFlags.put("lab10", "Flag{Clipboard_Exposure_Success}");
        labFlags.put("lab11", "Flag{Contacts_Leaked_Via_App}");
        labFlags.put("lab12", "Flag{Encrypted_Log_Extraction_Success}");
        labFlags.put("lab13", "Flag{Visa_Info_Reverse_Success}");
        labFlags.put("lab14", "Flag{Root_Blocked_Logic_Bypassed}");
        labFlags.put("lab15", "Flag{Hooked_And_Won}");
        labFlags.put("lab16", "Flag{SharedPrefs_Not_Safe}");
        labFlags.put("lab17", "Flag{Extracted_From_SQLite}");
        labFlags.put("lab18", "Flag{JWT_Extracted_From_Public_Storage}");
        labFlags.put("lab19", "Flag{Encrypted_ECB_Stored_Successfully}");
        labFlags.put("lab20", "Flag{PIN_With_Hardcoded_Key}");
        labFlags.put("lab21", "Flag{Bypassed_Encrypted_Check}");
    }

    public static String getFlag(String labName) {
        String key = labName.trim().toLowerCase(); // chuẩn hóa input
        if (labFlags.containsKey(key)) {
            return labFlags.get(key);
        } else {
            return "Không tìm thấy flag cho lab: " + labName;
        }
    }
}