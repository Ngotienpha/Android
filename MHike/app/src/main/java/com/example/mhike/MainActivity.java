package com.example.mhike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btn2;
    Button btn, update, delete;
    EditText name, location, edate, length, level;
    RadioGroup radioGroup;
    int checkbox;
    DBHelper DB;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.inputName);
        location = findViewById(R.id.inputLocation);
        edate = findViewById(R.id.inputDate);
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

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        length = findViewById(R.id.inputLength);
        level = findViewById(R.id.inputLevel);
        update = findViewById(R.id.updateBtn);
        delete = findViewById(R.id.deleteBtn);
        btn = findViewById(R.id.detailBtn);
        DB = new DBHelper(this);
        btn2 = findViewById(R.id.viewBtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                checkbox = i;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nameTxt = name.getText().toString();
                    String locationTxt = location.getText().toString();
                    String dateTxt = edate.getText().toString();
                    String lengthTxt = length.getText().toString();
                    String levelTxt = level.getText().toString();

                    Boolean checkinsertdata = DB.insertuserdata(nameTxt, locationTxt, dateTxt, lengthTxt, levelTxt);
                    if(checkinsertdata==true)
                    {
                        Toast.makeText(MainActivity.this, "New Entry Save", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "New Entry Not Save", Toast.LENGTH_SHORT).show();
                    }

                    if (name.getText().toString().length() == 0){
                        name.setError("This field can't be empty");
                    } else if (location.getText().toString().length() == 0){
                        location.setError("This field can't be empty");
                    } else if (edate.getText().toString().length() == 0){
                        edate.setError("This field can't be empty");
                    } else if (length.getText().toString().length() == 0){
                        length.setError("This field can't be empty");
                    } else if (level.getText().toString().length() == 0){
                        level.setError("This field can't be empty");
                    } else if (checkbox == 0){
                        Toast.makeText(MainActivity.this, "Please select parking available", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(MainActivity.this, DetailActivity.class));
                    }
                }
            });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String locationTxt = location.getText().toString();
                String dateTxt = edate.getText().toString();
                String lengthTxt = length.getText().toString();
                String levelTxt = level.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTxt, locationTxt, dateTxt, lengthTxt, levelTxt);
                if(checkupdatedata==true)
                {
                    Toast.makeText(MainActivity.this, "New Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                }

                if (name.getText().toString().length() == 0){
                    name.setError("This field can't be empty");
                } else if (location.getText().toString().length() == 0){
                    location.setError("This field can't be empty");
                } else if (edate.getText().toString().length() == 0){
                    edate.setError("This field can't be empty");
                } else if (length.getText().toString().length() == 0){
                    length.setError("This field can't be empty");
                } else if (level.getText().toString().length() == 0){
                    level.setError("This field can't be empty");
                } else if (checkbox == 0){
                    Toast.makeText(MainActivity.this, "Please select parking available", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(MainActivity.this, DetailActivity.class));
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();

                Boolean checkdeletedata = DB.deleteuserdata(nameTxt);
                if(checkdeletedata==true)
                {
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }

                if (name.getText().toString().length() == 0){
                    name.setError("This field can't be empty");
                } else {
                    startActivity(new Intent(MainActivity.this, DetailActivity.class));
                }
            }
        });
        }

    private void updatelabel() {
        String myFormat = "MM/dd/yy EEEE";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        edate.setText(dateFormat.format(myCalendar.getTime()));
    }

}