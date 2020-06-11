package com.example.kudian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mymusic = (Button) findViewById(R.id.mymusic);
        mymusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomepageActivity.this,MainActivity_y_Activity.class);
                startActivity(i);
            }
        });



        Button friend = (Button) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomepageActivity.this, FriendActivity.class);
                startActivity(i);
            }
        });

        ImageView search =  findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomepageActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        Button editor = (Button) findViewById(R.id.editor);
        editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomepageActivity.this, EditorActivity.class);
                startActivity(i);
            }
        });

        Button recommended = (Button) findViewById(R.id.recommended);
        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomepageActivity.this, RecommendedActivity.class);
                startActivity(i);
            }
        });

    }
}
