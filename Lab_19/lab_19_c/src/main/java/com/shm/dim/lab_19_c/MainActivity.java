package com.shm.dim.lab_19_c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSendA(View view) {
        // Send Activity application B
        Intent intent = new Intent();
        intent.setClassName("com.shm.dim.lab_19_b", "com.shm.dim.lab_19_b.MainActivity");
        startActivity(intent);
    }
}