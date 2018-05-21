package com.shm.dim.lab_17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    ScrollView mScrollView;
    TextView mConsole;
    EditText mCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScrollView = findViewById(R.id.scroll_view);
        mConsole = findViewById(R.id.console);
        mCommand = findViewById(R.id.command);

        mConsole.setText("Terminal started!");
    }


    public String executeCommand(String commandValue){
        Process process;
        StringBuilder output = new StringBuilder();
        String result = "";

        try {
            process = Runtime.getRuntime().exec(commandValue);
            process.waitFor();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                while ((result = bufferedReader.readLine()) != null) {
                    output.append(result + "\n");
                }
            }
        } catch (IOException | InterruptedException e){
            return e.getMessage();
        }

        return output.toString();
    }


    public void onClickRun(View view){
        String commandValue = mCommand.getText().toString();
        String commandResult = executeCommand(commandValue);
        mConsole.setText(mConsole.getText() +"\n" + commandResult);
        mCommand.setText("");
        mScrollView.fullScroll(View.FOCUS_DOWN);
    }
}