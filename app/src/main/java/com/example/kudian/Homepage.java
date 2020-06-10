package com.example.kudian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button music2 = (Button) findViewById(R.id.music2);
        music2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homepage.this,MainActivity_y.class);
                startActivity(i);
            }
        });



        Button friend = (Button) findViewById(R.id.friend);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homepage.this,Friend.class);
                startActivity(i);
            }
        });

       //Button search = (Button) findViewById(R.id.);
        //search.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
                //Intent i = new Intent(Homepage.this,);
               // startActivity(i);
           // }
       // });

        Button editor = (Button) findViewById(R.id.editor);
        editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homepage.this,Editor.class);
                startActivity(i);
            }
        });

        Button playlist = (Button) findViewById(R.id.playlist);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Homepage.this,Recommended.class);
                startActivity(i);
            }
        });

    }
}
