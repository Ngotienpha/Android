package com.example.mhike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Detail2Activity extends AppCompatActivity {
    RecyclerView list;
    ObservationAdapter adapter;
    DBHelper2 dbHelper2;
    ArrayList<String> name1, name2, name3, time, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        dbHelper2 = new DBHelper2(this);
        name1 = new ArrayList<>();
        name2 = new ArrayList<>();
        name3 = new ArrayList<>();
        time = new ArrayList<>();
        comment = new ArrayList<>();
        list = findViewById(R.id.recycler);
        adapter = new ObservationAdapter(this, name1, name2, name3, time, comment);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = dbHelper2.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(Detail2Activity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                name1.add(cursor.getString(0));
                name2.add(cursor.getString(1));
                name3.add(cursor.getString(2));
                time.add(cursor.getString(3));
                comment.add(cursor.getString(4));
            }
        }
    }
}