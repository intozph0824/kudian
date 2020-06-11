package com.example.kudian;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FriendActivity extends HomepageActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FriendActivity.this, HomepageActivity.class);
                startActivity(i);
            }
        });

        Button editor = (Button) findViewById(R.id.editor);
        editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FriendActivity.this, HomepageActivity.class);
                startActivity(i);
            }
        });

        ImageView search =  findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FriendActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
    }
}
