package com.example.shubhangi.homepage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ac1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Button datebtn;
    int year_x, month_x, day_x;
    int year_c, month_c, day_c;

    private Button submit=null;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    Spinner spin=null;
    CheckBox c1=null;
    CheckBox c2=null;
    TextView dtext=null;
    static final int Dialogue_id = 0;
    String date=null;
    String service_person=null;
    String services_required="null";
    String time=null;
    String status="GENERATED";
    String employee_name="null";
    String userid=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac1);
        submit = (Button)findViewById(R.id.submit1);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("Complaints");
      //  submit.setOnClickListener(this);
        //==================date selection=========================================
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        year_c = cal.get(Calendar.YEAR);
        month_c = cal.get(Calendar.MONTH);
        day_c = cal.get(Calendar.DAY_OF_MONTH);

        showdialoguemethod();
    //===================getting service person value through intent=========================
        Intent intent=getIntent();
        service_person = intent.getStringExtra("W5");

        //=====================get services required=================================
        c1=(CheckBox)findViewById(R.id.c1);
        c2=(CheckBox)findViewById(R.id.c2);
        //c1.setText("Leakage");
        //c2.setText("Cooling");

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c1.setChecked(false);
        c2.setChecked(false);
        //=============================get spinner time=============================

        spin=(Spinner)findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(this);
    }

    public void submitupdate(View v)
    {
            System.out.println("hello");
            saveComplaintInfo();
            finish();
            startActivity(new Intent(this,complaint1.class));
        Toast.makeText(this, "Submit button", Toast.LENGTH_SHORT).show();

    }

    private void saveComplaintInfo()
    {
        FirebaseUser user= firebaseAuth.getCurrentUser();
            if(user!=null) {
                //UserProfileChangeRequest.Builder profile = new UserProfileChangeRequest.Builder().setDisplayName(name1);
                //String id=databaseReference.push().getKey();
                userid=firebaseAuth.getUid();
                complaint c = new complaint(userid, service_person, services_required, date, time, status, employee_name);
                //databaseReference.child(id).setValue(s);
                String id=databaseReference.push().getKey();
                databaseReference.child(id).setValue(c);
                Toast.makeText(this, "Complaint Registered", Toast.LENGTH_SHORT).show();
            }
    }


    //=====================================================================================
    public void showdialoguemethod() {
        datebtn = (Button) findViewById(R.id.button);

        datebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                showDialog(Dialogue_id);
            }

        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == Dialogue_id)
            return new DatePickerDialog(this, dpickerlistner, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerlistner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year_x = i;
            month_x = i1 + 1;
            day_x = i2;
            if (year_x != year_c)
                Toast.makeText(ac1.this, "INVALID DATE", Toast.LENGTH_LONG).show();
            else if (month_x < month_c + 1)
                Toast.makeText(ac1.this, "INVALID DATE", Toast.LENGTH_LONG).show();
            else if (month_x == month_c + 1) {
                if (day_x <= day_c)
                    Toast.makeText(ac1.this, "INVALID DATE", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(ac1.this, day_x + "/ " + month_x + "/ " + year_x, Toast.LENGTH_LONG).show();
                    date=day_x + "/ " + month_x + "/ " + year_x;
                    dtext=(TextView)findViewById(R.id.dtext);
                    dtext.setText(date);
                    //Intent intent = new Intent(ac1.this, complaint.class);
                    //startActivity(intent);
                }
            }


        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        time=String.valueOf(spin.getSelectedItem());
        Toast.makeText(this,
                "OnClickListener : " +
                        "\nSpinner 1 : "+ String.valueOf(spin.getSelectedItem()),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }


    @Override
    public void onClick(View v)
    {
        if(c1.isChecked())
        {
            if(services_required.equals("null"))
                services_required="Leakage";
            else
                services_required=services_required+", Leakage";
        }
        if(c2.isChecked())
        {
            if(services_required.equals("null"))
                services_required="Cooling";
            else
                services_required=services_required+", Cooling";
        }
    }   
}
