package com.example.shubhangi.homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class complaint1 extends AppCompatActivity {

    Button newcomp = null;
    Button oldcomp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint1);

        newcomp=(Button)findViewById(R.id.newcomp);
        newcomp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(complaint1.this, complaint2.class);

                startActivity(intent);
            }


        });
        oldcomp=(Button)findViewById(R.id.oldcomp);
        oldcomp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(complaint1.this, date.class);

                startActivity(intent);
            }


        });


    }


}
