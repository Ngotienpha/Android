package com.example.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ObservationActivity extends AppCompatActivity {
    Button save, view, update, delete;
    EditText name1, name2, name3, datetime, comment;
    DBHelper2 dbHelper2;
    Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);
        name1 = findViewById(R.id.Add1);
        name2 = findViewById(R.id.Add2);
        name3 = findViewById(R.id.Add3);
        datetime = findViewById(R.id.Time);
        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updatelabel();
            }
        };

        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ObservationActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(ObservationActivity.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        datetime.setText(time);
                    }
                },hours ,mins, false);
                timePickerDialog.show();
            }
        });
        comment = findViewById(R.id.comment);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        dbHelper2 = new DBHelper2(this);
        save = findViewById(R.id.saveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1Txt = name1.getText().toString();
                String name2Txt = name2.getText().toString();
                String name3Txt = name3.getText().toString();
                String timeTxt = datetime.getText().toString();
                String commentTxt = comment.getText().toString();

                Boolean checkinsertdata = dbHelper2.insertuserdata(name1Txt, name2Txt, name3Txt, timeTxt, commentTxt);
                if(checkinsertdata==true)
                {
                    Toast.makeText(ObservationActivity.this, "New Entry Save", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ObservationActivity.this, "New Entry Not Save", Toast.LENGTH_SHORT).show();
                }

                if(name1.getText().toString().length() == 0){
                    name1.setError("This field can't be empty");
                } else if (datetime.getText().toString().length() == 0){
                    datetime.setError("This field can't be empty");
                } else {
                    startActivity(new Intent(ObservationActivity.this, Detail2Activity.class));
                }
            }
        });
        view = findViewById(R.id.viewBtn);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ObservationActivity.this, Detail2Activity.class));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1Txt = name1.getText().toString();
                String name2Txt = name2.getText().toString();
                String name3Txt = name3.getText().toString();
                String timeTxt = datetime.getText().toString();
                String commentTxt = comment.getText().toString();

                Boolean checkupdatedata = dbHelper2.updateuserdata(name1Txt, name2Txt, name3Txt, timeTxt, commentTxt);
                if(checkupdatedata==true)
                {
                    Toast.makeText(ObservationActivity.this, "New Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ObservationActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                }

                if(name1.getText().toString().length() == 0){
                    name1.setError("This field can't be empty");
                } else if (datetime.getText().toString().length() == 0){
                    datetime.setError("This field can't be empty");
                } else {
                    startActivity(new Intent(ObservationActivity.this, Detail2Activity.class));
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1Txt = name1.getText().toString();
                String name2Txt = name2.getText().toString();
                String name3Txt = name3.getText().toString();

                Boolean checkdeletedata = dbHelper2.deleteuserdata(name1Txt, name2Txt, name3Txt);
                if(checkdeletedata==true)
                {
                    Toast.makeText(ObservationActivity.this, "New Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ObservationActivity.this, "New Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }

                if(name1.getText().toString().length() == 0){
                    name1.setError("This field can't be empty");
                }else{
                startActivity(new Intent(ObservationActivity.this, Detail2Activity.class));
                }
            }
        });
    }

    private void updatelabel() {
        String myFormat = "MM/dd/yy EEEE k:mm a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        datetime.setText(dateFormat.format(myCalendar.getTime()));
    }
}