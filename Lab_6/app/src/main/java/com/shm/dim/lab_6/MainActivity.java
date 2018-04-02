package com.shm.dim.lab_6;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText mId, mValue;
    private TextView mText;
    private DbHelper dbHelper;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mId = findViewById(R.id.id);
        mValue = findViewById(R.id.value);
        mText = findViewById(R.id.text);

        dbHelper = new DbHelper(this);
        database = dbHelper.getWritableDatabase();
    }


    public void read_onClick(View view) {
        String outStr = new String();

        Cursor cursor = database.rawQuery("select * from STUDENTS", null);
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex("_ID");
            int value = cursor.getColumnIndex("VALUE");
            do {
                outStr += cursor.getString(id) + " : ";
                outStr += cursor.getString(value) + "\n";
            } while (cursor.moveToNext());
        }
        cursor.close();

        mText.setText(outStr);
    }

    public void add_onClick(View view) {
        ContentValues newValues = new ContentValues();

        newValues.put("_ID", mId.getText().toString());
        newValues.put("VALUE", mValue.getText().toString());

        database.insert("STUDENTS", null, newValues);
    }

    public void delete_onClick(View view) {
        if(mId.getText().toString() != "") {
            database.delete("STUDENTS", "_ID = ?",
                    new String[]{mId.getText().toString()});
        }
    }

    public void update_onClick(View view) {
        if(mId.getText().toString() != "" && mValue.getText().toString() != "") {
            ContentValues newValues = new ContentValues();

            newValues.put("VALUE", mValue.getText().toString());

            database.update("STUDENTS", newValues, "_ID = ?",
                    new String[]{mId.getText().toString()});
        }
    }
}