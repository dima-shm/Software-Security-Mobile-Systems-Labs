package com.shm.dim.lab_19_b;

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

    public void onClickSendActivityA(View view) {
        // Send Activity application A
        Intent intent = new Intent();
        intent.setClassName("com.shm.dim.lab_19", "com.shm.dim.lab_19.MainActivity");
        startActivity(intent);
    }

    public void onClickSendPrivilegedActivityA(View view) {
        // Send Privileged Activity application A
        Intent intent = new Intent();
        intent.setClassName("com.shm.dim.lab_19", "com.shm.dim.lab_19.PrivilegedActivity");
        startActivity(intent);
    }
}