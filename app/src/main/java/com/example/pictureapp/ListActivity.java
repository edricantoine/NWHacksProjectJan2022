package com.example.pictureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.pictureapp.adapter.recycleAdaper;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<CharSequence> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        items = MainActivity.getArr();
        RecyclerView rv = findViewById(R.id.recyclerView);
        recycleAdaper ra = new recycleAdaper(items, this);
        rv.setAdapter(ra);
        rv.setHasFixedSize(true);
    }


}