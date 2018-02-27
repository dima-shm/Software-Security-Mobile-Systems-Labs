package com.shm.dim.lab_2_encode;

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

    private EditText mText, mEncodedText;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = findViewById(R.id.text);
        mEncodedText = findViewById(R.id.encoded_text);

        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Lab_2_key.txt");
    }


    private SecretKeySpec generateSecretKey() {
        SecretKeySpec secretKey = null;
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed("any data used as random seed".getBytes());
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, secureRandom);
            secretKey = new SecretKeySpec((keyGenerator.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e("Log_02", "AES secret key spec error");
        }
        return secretKey;
    }

    private byte[] encodeText(SecretKeySpec secretKey, String text) {
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, secretKey);
            encodedBytes = c.doFinal(text.getBytes());
        } catch (Exception e) {
            Log.e("Log_02", "AES encryption error");
        }
        return encodedBytes;
    }


    public void onClickEncode(View view) {
        EditText etext = findViewById(R.id.text);
        String text = etext.getText().toString();


        SecretKeySpec secretKey = generateSecretKey();
        String stringKey = Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
        FileHelper.write(file, stringKey);


        byte[] encodedBytes = encodeText(secretKey, text);


        EditText encoded = findViewById(R.id.encoded_text);
        encoded.setText(Base64.encodeToString(encodedBytes, Base64.DEFAULT));
    }
}