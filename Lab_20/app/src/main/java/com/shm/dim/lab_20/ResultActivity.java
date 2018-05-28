package com.shm.dim.lab_20;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mList = findViewById(R.id.list);

        Bundle arguments = getIntent().getExtras();
        ArrayList<String> data = arguments.getStringArrayList("list");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        mList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
