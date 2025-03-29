package com.example.ctf_challenge_app.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.ctf.Lab2ApiTokenDecoderActivity;
import com.example.ctf_challenge_app.ctf.Lab1HardCorePasswordActivity;
import com.example.ctf_challenge_app.ctf.Lab10VulClipboardActivity;
import com.example.ctf_challenge_app.ctf.Lab11ContactsLeakActivity;
import com.example.ctf_challenge_app.ctf.Lab12UserLeakFromLogFileActivity;
import com.example.ctf_challenge_app.ctf.Lab13InsufficientProtectionActivity;
import com.example.ctf_challenge_app.ctf.Lab14CheckRootActivity;
import com.example.ctf_challenge_app.ctf.Lab15HookFridaVerifyActivity;
import com.example.ctf_challenge_app.ctf.Lab16InsecureStorageActivity;
import com.example.ctf_challenge_app.ctf.Lab17SqliteAccessActivity;
import com.example.ctf_challenge_app.ctf.Lab18JwtTokenCheckActivity;
import com.example.ctf_challenge_app.ctf.Lab19AesEcbDecryptActivity;
import com.example.ctf_challenge_app.ctf.Lab20HardcodedKeyActivity;
import com.example.ctf_challenge_app.ctf.Lab21BypassCryptoCheckActivity;
import com.example.ctf_challenge_app.ctf.Lab4ByPassSmaliActivity;
import com.example.ctf_challenge_app.ctf.Lab5AuthenticationWithoutPermissionActivity;
import com.example.ctf_challenge_app.ctf.Lab6FridaForAuthenticationActivity;
import com.example.ctf_challenge_app.ctf.Lab7InputCheckActivity;
import com.example.ctf_challenge_app.ctf.Lab8XssAttackActivity;
import com.example.ctf_challenge_app.ctf.Lab9OutOfRangeDataActivity;
import com.example.ctf_challenge_app.ctf.Lab3LoginLogActivity;
import com.example.ctf_challenge_app.models.Lab;

import java.util.ArrayList;

public class LabAdapter  extends ArrayAdapter<Lab> {
    Activity ctx;
    int IdLayout;
    ArrayList<Lab> myList;

    public LabAdapter(Activity ctx, int idLayout, ArrayList<Lab> myList) {
        super(ctx, idLayout, myList);
        this.ctx = ctx;
        IdLayout = idLayout;
        this.myList = myList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        LayoutInflater myInflater = ctx.getLayoutInflater();

        convertView = myInflater.inflate(IdLayout, null);

        Lab lab = myList.get(position);


        TextView txt_lab_title = convertView.findViewById(R.id.lab_title);
        txt_lab_title.setText(lab.getTitle());

        TextView txt_lab_description = convertView.findViewById(R.id.lab_description);
        txt_lab_description.setText(lab.getDescription());


        Button button = convertView.findViewById(R.id.action_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = null; // Khởi tạo Intent là null

                switch (lab.getLabKey()) {
                    case "lab_1":
                        myIntent = new Intent(ctx, Lab1HardCorePasswordActivity.class);
                        break;
                    case "lab_2":
                        myIntent = new Intent(ctx, Lab2ApiTokenDecoderActivity.class);
                        break;
                    case "lab_3":
                        myIntent = new Intent(ctx, Lab3LoginLogActivity.class);
                        break;
                    case "lab_4":
                        myIntent = new Intent(ctx, Lab4ByPassSmaliActivity.class);
                        break;
                    case "lab_5":
                        myIntent = new Intent(ctx, Lab5AuthenticationWithoutPermissionActivity.class);
                        break;
                    case "lab_6":
                        myIntent = new Intent(ctx, Lab6FridaForAuthenticationActivity.class);
                        break;
                    case "lab_7":
                        myIntent = new Intent(ctx, Lab7InputCheckActivity.class);
                        break;
                    case "lab_8":
                        myIntent = new Intent(ctx, Lab8XssAttackActivity.class);
                        break;
                    case "lab_9":
                        myIntent = new Intent(ctx, Lab9OutOfRangeDataActivity.class);
                        break;
                    case "lab_10":
                        myIntent = new Intent(ctx, Lab10VulClipboardActivity.class);
                        break;
                    case "lab_11":
                        myIntent = new Intent(ctx, Lab11ContactsLeakActivity.class);
                        break;
                    case "lab_12":
                        myIntent = new Intent(ctx, Lab12UserLeakFromLogFileActivity.class);
                        break;
                    case "lab_13":
                        myIntent = new Intent(ctx, Lab13InsufficientProtectionActivity.class);
                        break;
                    case "lab_14":
                        myIntent = new Intent(ctx, Lab14CheckRootActivity.class);
                        break;
                    case "lab_15":
                        myIntent = new Intent(ctx, Lab15HookFridaVerifyActivity.class);
                        break;
                    case "lab_16":
                        myIntent = new Intent(ctx, Lab16InsecureStorageActivity.class);
                        break;
                    case "lab_17":
                        myIntent = new Intent(ctx, Lab17SqliteAccessActivity.class);
                        break;
                    case "lab_18":
                        myIntent = new Intent(ctx, Lab18JwtTokenCheckActivity.class);
                        break;
                    case "lab_19":
                        myIntent = new Intent(ctx, Lab19AesEcbDecryptActivity.class);
                        break;
                    case "lab_20":
                        myIntent = new Intent(ctx, Lab20HardcodedKeyActivity.class);
                        break;
                    case "lab_21":
                        myIntent = new Intent(ctx, Lab21BypassCryptoCheckActivity.class);
                        break;
                    default:
                        Toast.makeText(v.getContext(), "Lab này chưa được cấu hình!", Toast.LENGTH_SHORT).show();
                        break;
                }

                if (myIntent != null) { // Kiểm tra nếu Intent hợp lệ mới startActivity
                    ctx.startActivity(myIntent);
                }
            }
        });

        return  convertView;

    }
}
