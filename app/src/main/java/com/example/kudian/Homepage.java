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

        Button music1 = (Button) findViewById(R.id.music1);
        music1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Homepage.this,);
                //startActivity(i);
            }
        });

        Button music2 = (Button) findViewById(R.id.music2);
        music2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Homepage.this,);
               // startActivity(i);
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
            //public void onClick(View v) {
                //Intent i = new Intent(Homepage.this,);
                //startActivity(i);
            }
        //});

       // Button editor = (Button) findViewById(R.id.);
       // editor.setOnClickListener(new View.OnClickListener() {
           // @Override
            public void onClick(View v) {
                //Intent i = new Intent(Homepage.this,);
                //startActivity(i);
            }
        //});

    }
//}
