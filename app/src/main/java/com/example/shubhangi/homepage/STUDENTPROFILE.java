package com.example.shubhangi.homepage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class STUDENTPROFILE extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView name, roomNo,contactNo,emailID,gender,helloName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprofile);
        name = (TextView) findViewById(R.id.TV2);
        gender = (TextView) findViewById(R.id.TV3);
        emailID = (TextView) findViewById(R.id.TV4);
        contactNo = (TextView) findViewById(R.id.TV5);
        roomNo = (TextView) findViewById(R.id.TV6);
        //helloName=(TextView)findViewById(R.id.helloName);
        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Student").child(mAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
                name.setText(student.getName2());
                roomNo.setText(student.getRoomNo());
                gender.setText(student.getGender2());
                emailID.setText(student.getEmail());
                contactNo.setText(student.getContactNo());
                //helloName.setText("Hello "+student.getName2());
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(STUDENTPROFILE.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        //databaseReference=FirebaseDatabase.getInstance().getReference("Name");

        //tv1.setText("SAMYAK");

        //tv2.setText("MALE");

        //tv3.setText("SAMYAKJAIN18045@IIITD.AC.IN");

        //tv4.setText("9555030033");

        //tv5.setText("602");

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i1 = new Intent(STUDENTPROFILE.this,ResetPassword.class);
                startActivity(i1);
            }

        });

        Button btn1 = (Button) findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(STUDENTPROFILE.this,CHANGECONTACT.class);
                startActivity(in);
            }

        });

        Button btn2 = (Button) findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in1 = new Intent(STUDENTPROFILE.this,CHANGEROOM.class);
                startActivity(in1);
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }

    }

}

