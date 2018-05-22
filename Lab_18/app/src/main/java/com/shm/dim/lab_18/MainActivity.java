package com.shm.dim.lab_18;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText mKey, mValue;
    private static final String MY_PREFERENCES = "MyPrefs" ;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mKey = findViewById(R.id.key);
        mValue = findViewById(R.id.value);

        mSharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
    }


    public final double distance(final String s1, final String s2) {
        if (s1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (s2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (s1.equals(s2)) {
            return 0;
        }

        if (s1.length() == 0) {
            return s2.length();
        }

        if (s2.length() == 0) {
            return s1.length();
        }

        int[] v0 = new int[s2.length() + 1];
        int[] v1 = new int[s2.length() + 1];
        int[] vtemp;

        for (int i = 0; i < v0.length; i++) {
            v0[i] = i;
        }

        for (int i = 0; i < s1.length(); i++) {
            v1[0] = i + 1;

            for (int j = 0; j < s2.length(); j++) {
                int cost = 1;
                if (s1.charAt(i) == s2.charAt(j)) {
                    cost = 0;
                }
                v1[j + 1] = Math.min(
                        v1[j] + 1,
                        Math.min(
                                v0[j + 1] + 1,
                                v0[j] + cost));
            }

            vtemp = v0;
            v0 = v1;
            v1 = vtemp;
        }

        return v0[s2.length()];
    }


    public void onClickSave(View view) {
        String key  = mKey.getText().toString();
        String value  = mValue.getText().toString();

        if(!key.equals("") && !value.equals("")) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();

            mKey.setText("");
            mValue.setText("");

            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Enter values", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickFind(View view) {
        String key  = mKey.getText().toString();

        if(!key.equals("")) {

            Map<String, ?> allPreferences = mSharedPreferences.getAll();
            Set<String> keySet = allPreferences.keySet();
            String[] keyArray = keySet.toArray(new String[keySet.size()]);

            for(int i = 0; i < allPreferences.size(); i++) {
                if(distance(keyArray[i], key) <= 1) {
                    mValue.setText(mSharedPreferences.getString(keyArray[i], null));
                    return;
                }
            }
            Toast.makeText(MainActivity.this, "Name not found", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Enter name", Toast.LENGTH_LONG).show();
        }
    }
}