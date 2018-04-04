package com.shm.dim.lab_9;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mPhoneNumber,
            mMessageText;


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                enablePermissions();
            }
        }
    }

    public void enablePermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhoneNumber = findViewById(R.id.phone_number);
        mMessageText = findViewById(R.id.text_message);
    }


    public void onClick_SendMessage(View view) {
        String phone = mPhoneNumber.getText().toString();
        String text = mMessageText.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, text, null, null);

        Toast.makeText(this, "Message is sent", Toast.LENGTH_SHORT).show();
    }
}