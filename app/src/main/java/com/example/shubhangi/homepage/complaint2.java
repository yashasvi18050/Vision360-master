package com.example.shubhangi.homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class complaint2 extends AppCompatActivity {

    Button mason =null;
    Button carp=null;
    Button elect=null;
    Button plumb=null;
    Button ac=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint2);

        mason=(Button)findViewById(R.id.mason);
        mason.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(complaint2.this, mason1.class);
                intent.putExtra("W1","MASON");

                startActivity(intent);
            }


        });
        carp=(Button)findViewById(R.id.carpenter);
        carp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(complaint2.this, carp.class);
                intent.putExtra("W2","CARPENTER");
                startActivity(intent);
            }


        });
        elect=(Button)findViewById(R.id.electric);
        elect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(complaint2.this, elect.class);
                intent.putExtra("W3","ELECTRICIAN");
                startActivity(intent);
            }


        });
        plumb=(Button)findViewById(R.id.plumber);
        plumb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(complaint2.this, plumber.class);
                intent.putExtra("W4","PLUMBER");
                startActivity(intent);
            }


        });
        ac=(Button)findViewById(R.id.ac);
        ac.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(complaint2.this, ac1.class);
                intent.putExtra("W5","AIR-CONDITIONER");
                startActivity(intent);
            }


        });
    }
}
