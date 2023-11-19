package com.example.mhike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name, location, date, length, level;
    DBHelper DB;
    MyAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        location = new ArrayList<>();
        date = new ArrayList<>();
        length = new ArrayList<>();
        level = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        searchView = findViewById(R.id.searchbar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        adapter = new MyAdapter(this, name, location, date, length, level);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void filter(String newText) {
        ArrayList<String> filteredList = new ArrayList<>();
        for (String item : name) {
            if (item.toString().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(DetailActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                name.add(cursor.getString(0));
                location.add(cursor.getString(1));
                date.add(cursor.getString(2));
                length.add(cursor.getString(3));
                level.add(cursor.getString(4));
            }
        }
    }
}
