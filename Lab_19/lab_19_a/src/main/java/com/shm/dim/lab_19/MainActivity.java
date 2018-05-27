package com.shm.dim.lab_19;

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

    public void onClickSendB(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.shm.dim.lab_19_b", "com.shm.dim.lab_19_b.PrivilegedActivity");
        startActivity(intent);
    }
}