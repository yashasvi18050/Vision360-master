package com.example.shubhangi.homepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegisteration extends AppCompatActivity implements View.OnClickListener {


    private EditText name;
    private EditText gender;
    private EditText contact;
    private EditText roomNo;

    private Button save;

    private  FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registeration);

        name=(EditText)findViewById(R.id.name);
        gender=(EditText)findViewById(R.id.gender);
        contact=(EditText)findViewById(R.id.contactNo);
        roomNo=(EditText)findViewById(R.id.roomNo);

        save = (Button)findViewById(R.id.saveButton);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("Student");

        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==save){
            saveUserInfo();
            finish();
            startActivity(new Intent(this,main2.class));
        }
    }

    private void saveUserInfo() {
        //Firebase ref = new Firebase(Config.FIREBASE_URL);
        String name1=name.getText().toString().trim();
        if(name1.isEmpty()){
            name.setError("Name Required");
            name.requestFocus();
            return;
        }
        String gender1=gender.getText().toString().trim();
        if(gender1.isEmpty()){
            gender.setError("Gender Required");
            gender.requestFocus();
            return;
        }
        String contact1=contact.getText().toString().trim();
        if(contact1.isEmpty()){
            contact.setError("Contact No required");
            contact.requestFocus();
            return;
        }
        String roomNo1=roomNo.getText().toString().trim();
        if(roomNo1.isEmpty()){
            roomNo.setError("Room No required");
            roomNo.requestFocus();
            return;
        }

        FirebaseUser user= firebaseAuth.getCurrentUser();
        if(user!=null){
            //UserProfileChangeRequest.Builder profile = new UserProfileChangeRequest.Builder().setDisplayName(name1);
            //String id=databaseReference.push().getKey();
            Student s = new Student(name1,gender1,roomNo1,contact1,user.getEmail());
            databaseReference.child(firebaseAuth.getUid()).setValue(s);
            //databaseReference.setValue(s);
            Toast.makeText(this,"Data Added",Toast.LENGTH_SHORT).show();
        }



    }
}
