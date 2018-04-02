package com.shm.dim.lab_5;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridView mStudentsList;
    private String URL = "content://com.shm.dim.lab_5.College/students";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStudentsList = findViewById(R.id.students_list);
    }


    public void onClick_AddName(View view) {
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.name)).getText().toString());
        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.grade)).getText().toString());
        values.put(StudentsProvider.ADDRESS,
                ((EditText)findViewById(R.id.address)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClick_RetrieveStudents(View view) {
        mStudentsList.setNumColumns(4);
        mStudentsList.setAdapter(null);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        adapter.add("ID");
        adapter.add("NAME");
        adapter.add("GRADE");
        adapter.add("ADDR");

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        int id = c.getColumnIndex(StudentsProvider._ID);
        int name = c.getColumnIndex(StudentsProvider.NAME);
        int grade = c.getColumnIndex(StudentsProvider.GRADE);
        int address = c.getColumnIndex(StudentsProvider.ADDRESS);

        if (c.moveToFirst()) {
            do {
                adapter.add(c.getString(id));
                adapter.add(c.getString(name));
                adapter.add( c.getString(grade));
                adapter.add(c.getString(address));
            } while (c.moveToNext());
        }
        mStudentsList.setAdapter(adapter);
    }
}