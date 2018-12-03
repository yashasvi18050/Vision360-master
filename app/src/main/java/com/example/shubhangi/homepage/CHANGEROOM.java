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

public class CHANGEROOM extends AppCompatActivity {
    EditText room;
    Student student;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeroom);
        room= (EditText) findViewById(R.id.room);
        Button btn = (Button) findViewById(R.id.changeroom);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Student").child(mAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                student = dataSnapshot.getValue(Student.class);
                room.setText(student.getRoomNo());
                //helloName.setText("Hello "+student.getName2());
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(CHANGEROOM.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String room1 = room.getText().toString();
                Student student1 = new Student(student.getName2(),student.getGender2(),room1,student.getContactNo(),student.getEmail());
                databaseReference.setValue(student1);
                Toast.makeText(CHANGEROOM.this, "ROOM NO UPDATED", Toast.LENGTH_LONG).show();
                finish();
                Intent int1= new Intent(CHANGEROOM.this,STUDENTPROFILE.class);
                startActivity(int1);

            }
        });
    }
}
