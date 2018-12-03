package com.example.shubhangi.homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CHANGECONTACT extends AppCompatActivity {
    EditText number;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changecontact);
        number=(EditText) findViewById(R.id.mobile);
        Button btn = (Button) findViewById(R.id.button4);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Student").child(mAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               student = dataSnapshot.getValue(Student.class);
                number.setText(student.getContactNo());
                //helloName.setText("Hello "+student.getName2());
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(CHANGECONTACT.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(number.getText().toString().length()!=10)
                {
                    Toast.makeText(CHANGECONTACT.this,"WRONG NUMBER OF DIGITS",Toast.LENGTH_LONG).show();

                }

                else
                {
                    String contact = number.getText().toString();
                    Student student1 = new Student(student.getName2(),student.getGender2(),student.getRoomNo(),contact,student.getEmail());
                    databaseReference.setValue(student1);
                    Toast.makeText(CHANGECONTACT.this,"NUMBER UPDATED SUCCESSFULLY",Toast.LENGTH_LONG).show();
                    finish();
                    Intent int1= new Intent(CHANGECONTACT.this,STUDENTPROFILE.class);
                    startActivity(int1);

                }
            }


        });
    }
}
