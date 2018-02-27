package com.shm.dim.lab_2_decode;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private EditText mEncodedText, mDecodedText;
    private File file;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEncodedText = findViewById(R.id.encoded_text);
        mDecodedText = findViewById(R.id.decoded_text);

        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Lab_2_key.txt");
    }


    private String getDecodedText(byte[] encodedBytes, SecretKeySpec secretKey) {
        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, secretKey);
            decodedBytes = c.doFinal(encodedBytes);
        } catch (Exception e) {
            Log.e("Log_02", "AES decryption error");
        }
        return new String(decodedBytes);
    }


    public void onClickDecode(View view) {
        String encodedText = mEncodedText.getText().toString();


        String stringKey = FileHelper.read(file);
        byte[] encodedKey = Base64.decode(stringKey, Base64.DEFAULT);
        SecretKeySpec secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length,"AES");


        String decodedText = getDecodedText(Base64.decode(encodedText, Base64.DEFAULT), secretKey);
        mDecodedText.setText(decodedText);
    }
}