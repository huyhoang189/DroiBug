package com.example.ctf_challenge_app.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ctf_challenge_app.R;
import com.example.ctf_challenge_app.models.Challenge;

import java.util.ArrayList;

public class ChallengeAdapter extends ArrayAdapter<Challenge> {
    Activity ctx;
    int IdLayout;
    ArrayList<Challenge> myList;

    public ChallengeAdapter(Activity ctx, int idLayout, ArrayList<Challenge> myList) {
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

        Challenge challenge = myList.get(position);

        ImageView img_challenge = (ImageView) convertView.findViewById(R.id.listImage);
        img_challenge.setImageResource(challenge.getImageResId());

        TextView txt_challenge_title = convertView.findViewById(R.id.listChallengeType);
        txt_challenge_title.setText(challenge.getTitle());

        TextView txt_challenge_description = convertView.findViewById(R.id.listChallengeDesc);
        txt_challenge_description.setText(challenge.getDescription());

        return  convertView;

    }
}





