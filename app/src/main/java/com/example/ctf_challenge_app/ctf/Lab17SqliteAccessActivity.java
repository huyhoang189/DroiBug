package com.example.ctf_challenge_app.ctf;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

public class Lab17SqliteAccessActivity extends AppCompatActivity {


    EditText usernameEdt, passwordEdt;
    TextView resultText;
    Button loginBtn;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab17_sqlite_access);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameEdt = findViewById(R.id.username);
        passwordEdt = findViewById(R.id.password);
        resultText = findViewById(R.id.result_text);
        loginBtn = findViewById(R.id.login_btn);

        dbHelper = new DBHelper(this);
        dbHelper.insertDefaultUser(); // chỉ chèn khi chưa có

        loginBtn.setOnClickListener(v -> {
            String user = usernameEdt.getText().toString();
            String pass = passwordEdt.getText().toString();

            if (dbHelper.checkCredentials(user, pass)) {
                resultText.setText(FlagManager.getFlag("lab17"));
            } else {
                resultText.setText("Sai thông tin đăng nhập!");
            }
        });

        FloatingActionButton helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(v -> {
            Intent intent = new Intent(Lab17SqliteAccessActivity.this, HelpActivity.class);
            intent.putExtra("LAB_CODE", "lab_17");
            startActivity(intent);
        });
    }

    // SQLite helper nội bộ
    static class DBHelper extends SQLiteOpenHelper {

        private static final String DB_NAME = "userinfo.db";
        private static final int DB_VERSION = 1;
        private static final String TABLE = "users";

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + " (id INTEGER PRIMARY KEY, username TEXT, password TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

        public void insertDefaultUser() {
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{"ctfadmin"});
            if (!cursor.moveToFirst()) {
                db.execSQL("INSERT INTO users (username, password) VALUES ('ctfadmin', 'sqlpass123');");
            }
            cursor.close();
        }

        public boolean checkCredentials(String username, String password) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
            boolean match = cursor.moveToFirst();
            cursor.close();
            return match;
        }
    }
}

