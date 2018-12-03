package com.example.shubhangi.homepage;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity  {
    public static final String key = "user";

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        Button admin = findViewById(R.id.ADMIN);


        admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, login_second.class);

                startActivity(intent);
            }


        });


        final Button student = (Button) findViewById(R.id.STUDENT);
        student.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //String user = "STUDENT";

                Intent intent = new Intent(Login.this, login_second.class);
              startActivity(intent);
            }

        });



 }
}
