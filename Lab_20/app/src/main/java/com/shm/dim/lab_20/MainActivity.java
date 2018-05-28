package com.shm.dim.lab_20;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mText;
    private SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DBHelper(this).getWritableDatabase();

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            showResults(query);
        }

        mText = findViewById(R.id.text);
    }

    public void onClickAdd(View view) {
        String data = mText.getText().toString();
        if (!data.equals("")) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("data", data);

            database.insert("data", null, contentValues);

            mText.setText("");
        }
    }

    public void onClickSearch(View view) {
        onSearchRequested();
    }

    private void showResults(String query) {
        Cursor cursor = database.rawQuery("SELECT id, data FROM data WHERE data LIKE '%" + query + "%'", null);

        if(cursor.moveToFirst()) {
            ArrayList<String> data = new ArrayList<>();
            do {
                data.add(cursor.getString(1));
            } while (cursor.moveToNext());

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putStringArrayListExtra("list", data);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        }
    }
}