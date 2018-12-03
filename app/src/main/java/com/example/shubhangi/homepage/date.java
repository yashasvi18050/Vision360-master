package com.example.shubhangi.homepage;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import java.util.Calendar;

public class date extends AppCompatActivity {

    Button datebtn;
    int year_x, month_x, day_x;
    int year_c, month_c, day_c;

    static final int Dialogue_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        year_c = cal.get(Calendar.YEAR);
        month_c = cal.get(Calendar.MONTH);
        day_c = cal.get(Calendar.DAY_OF_MONTH);

        showdialoguemethod();


    }

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
                Toast.makeText(date.this, "INVALID DATE", Toast.LENGTH_LONG).show();
            else if (month_x < month_c + 1)
                Toast.makeText(date.this, "INVALID DATE", Toast.LENGTH_LONG).show();
            else if (month_x == month_c + 1) {
                if (day_x <= day_c)
                    Toast.makeText(date.this, "INVALID DATE", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(date.this, day_x + "/ " + month_x + "/ " + year_x, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(date.this, complaint.class);
                    startActivity(intent);
                }
            }


        }
    };
}