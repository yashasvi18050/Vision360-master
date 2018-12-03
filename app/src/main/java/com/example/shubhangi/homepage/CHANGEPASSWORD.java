package com.example.shubhangi.homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CHANGEPASSWORD extends AppCompatActivity {
    EditText oldpassword,newpassword,confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        oldpassword=(EditText) findViewById(R.id.oldpassword);
        newpassword=(EditText) findViewById(R.id.newpassword);
        confirmpassword=(EditText) findViewById(R.id.confirmpassword);


        Button btn = (Button) findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (newpassword.getText().toString().equals(confirmpassword.getText().toString()))
                {
                    Toast.makeText(CHANGEPASSWORD.this, "PASSWORD UPDATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(CHANGEPASSWORD.this,STUDENTPROFILE.class);
                    startActivity(i);
                }

                else
                {
                    Toast.makeText(CHANGEPASSWORD.this, "PASSWORD DIDNT MATCH", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
