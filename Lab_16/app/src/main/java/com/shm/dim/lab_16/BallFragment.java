package com.shm.dim.lab_16;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class BallFragment extends Fragment {

    TextView mTimer;
    ImageView mImage;

    public BallFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ball, container, false);

        mTimer = view.findViewById(R.id.timer);
        mImage = view.findViewById(R.id.image);

        startTimer();

        ObjectAnimator animation1 = new ObjectAnimator()
                .ofFloat(mImage, "rotation", 0f, 360f)
                .setDuration(3000);
        animation1.start();

        return view;
    }

    private void startTimer() {

        final String FORMAT = "%02d:%02d:%02d";

        new CountDownTimer(3000, 1000) {
            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                mTimer.setText(String.format(FORMAT,
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                mTimer.setText("");
            }
        }.start();
    }
}