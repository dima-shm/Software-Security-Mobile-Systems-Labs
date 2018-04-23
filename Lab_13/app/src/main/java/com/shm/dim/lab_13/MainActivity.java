package com.shm.dim.lab_13;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GestureOverlayView mGestureView;
    private GestureLibrary gestureLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureView = findViewById(R.id.gestures1);

        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        if (!gestureLib.load()) {
            finish();
        }

        mGestureView.addOnGesturePerformedListener( new GestureOverlayView.OnGesturePerformedListener() {
            public void onGesturePerformed(GestureOverlayView gestureView, Gesture gesture) {
                ArrayList<Prediction> predictions = gestureLib.recognize(gesture);

                if (predictions.size() > 0) {
                    Prediction prediction = predictions.get(0);
                    if (prediction.score > 1.0) {
                        Toast.makeText(MainActivity.this, "Your gesture: " + prediction.name, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}
