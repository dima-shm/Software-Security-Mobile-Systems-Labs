package com.shm.dim.lab_10;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView mSmsList;
    private EditText mText;
    private RadioGroup mLanguageGroup;
    private TextToSpeech textToSpeech;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSmsList = findViewById(R.id.sms_list);
        mText = findViewById(R.id.text);
        mLanguageGroup = findViewById(R.id.language_group);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getAllSms());
        mSmsList.setAdapter(adapter);
        mSmsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String smsText = ((TextView) view).getText().toString();
                mText.setText(smsText);
            }
        });

        mLanguageGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setTextToSpeechLanguage();
            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
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


    public List<String> getAllSms() {
        List<String> listSms = new ArrayList<>();

        ContentResolver cr = this.getContentResolver();

        Cursor c = cr.query(Uri.parse("content://sms/"), null, null, null, null);
        if (c != null) {
            while(c.moveToNext()) {
                listSms.add(c.getString(c.getColumnIndexOrThrow("body")));
            }
            c.close();
        }

        return listSms;
    }

    private Locale getUserSelectedLanguage() {
        int checkedRadioId = mLanguageGroup.getCheckedRadioButtonId();
        switch (checkedRadioId) {
            case R.id.russian:
                return new Locale("ru");
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