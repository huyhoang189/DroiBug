package com.example.ctf_challenge_app;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ctf_challenge_app.adapters.LabAdapter;
import com.example.ctf_challenge_app.databases.LabDAO;
import com.example.ctf_challenge_app.models.Lab;

import java.util.ArrayList;
import java.util.List;

public class ListPractice extends AppCompatActivity {

    ListView labListView;
    LabAdapter labAdapter;
    ArrayList<Lab> labs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_practice);
        labListView = findViewById(R.id.lab_listview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Nhận dữ liệu từ Intent
        String id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");

        TextView toolbar_txt = findViewById(R.id.toolbar);
        toolbar_txt.setText(title);



        labs = (ArrayList<Lab>) onCheckListLab(ListPractice.this, id);

        labAdapter = new LabAdapter(ListPractice.this, R.layout.lab_item, labs);
        labListView.setAdapter(labAdapter);
    }

    public List<Lab> onCheckListLab (Context ctx, String challenge_type) {
        LabDAO labDAO = new LabDAO(ctx);
        return labDAO.getLabsByChallengeType(challenge_type);
    }
}