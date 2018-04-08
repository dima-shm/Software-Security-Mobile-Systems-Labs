package com.shm.dim.lab_10;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    
    private EditText mText;
    private RadioGroup mLanguageGroup;
    private TextToSpeech textToSpeech;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = findViewById(R.id.text);
        mLanguageGroup = findViewById(R.id.language_group);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                setTextToSpeechLanguage();
            }
        });
        mLanguageGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setTextToSpeechLanguage();
            }
        });
    }

    @Override
    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }


    private Locale getUserSelectedLanguage() {
        int checkedRadioId = mLanguageGroup.getCheckedRadioButtonId();
        switch (checkedRadioId) {
            case R.id.english:
                return Locale.ENGLISH;
            case R.id.french:
                return Locale.ENGLISH;
            case R.id.german:
                return Locale.GERMAN;
            default:
                return null;
        }
    }

    private void setTextToSpeechLanguage() {
        Locale language = getUserSelectedLanguage();
        textToSpeech.setLanguage(language);
    }


    public void speakOnClick(View view) {
        String text = mText.getText().toString();
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}