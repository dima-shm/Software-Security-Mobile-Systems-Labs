package com.shm.dim.lab_1;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private int remainingAttempts = 3;
    private MediaPlayer mediaPlayer;
    private String colorCodes[] = new String[]{"#FF0000","#800000","#FFFF00","#876644"};

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById (R.id.user_name);
        password = findViewById (R.id.password);

        mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.sound);

        password.setTransformationMethod(new MyPasswordTransformationMethod());
        password.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password.setTextColor(Color.parseColor(generateRndColor(0, colorCodes.length - 1)));
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        try {
                            mp.prepare();
                        }
                        catch( IOException ie ) {
                            throw new RuntimeException(ie) ;
                        }

                    }
                });
            }

        });
    }

    public String generateRndColor(int min, int max) {
        return colorCodes[(int)(Math.random()*(max-min))+min];
    }

    int getDistLevenshtein(String S1, String S2) {
        int m = S1.length(), n = S2.length();
        int[] D1;
        int[] D2 = new int[n + 1];

        for(int i = 0; i <= n; i ++)
            D2[i] = i;

        for(int i = 1; i <= m; i ++) {
            D1 = D2;
            D2 = new int[n + 1];
            for(int j = 0; j <= n; j ++) {
                if(j == 0) D2[j] = i;
                else {
                    int cost = (S1.charAt(i - 1) != S2.charAt(j - 1)) ? 1 : 0;
                    if(D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
                        D2[j] = D2[j - 1] + 1;
                    else if(D1[j] < D1[j - 1] + cost)
                        D2[j] = D1[j] + 1;
                    else
                        D2[j] = D1[j - 1] + cost;
                }
            }
        }
        return D2[n];
    }

    public void loginClick(View view) {
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            remainingAttempts = 0;
            Toast.makeText(this,"Логин и пароль корректны", LENGTH_SHORT).show();
        } else if (getDistLevenshtein(password.getText().toString(), "admin") == 1) {
            remainingAttempts = 5;
            Toast.makeText(this,"Количество попыток увеличено до 5", LENGTH_SHORT).show();
        }
        else {
            remainingAttempts--;
            Toast.makeText(this,"Логин и пароль не корректны. Осталось попыток: " + remainingAttempts, LENGTH_SHORT).show();
            if(remainingAttempts == 0) {
                Toast.makeText(this,"Приложение было закрыто", LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
