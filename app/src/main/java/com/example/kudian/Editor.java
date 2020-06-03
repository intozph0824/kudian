package com.example.kudian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Editor extends Homepage{

    protected void onCteate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);

        Button editor = (Button) findViewById(R.id.editor);
        editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Editor.this,Homepage.class);
                startActivity(i);
            }
        });
    }
}
