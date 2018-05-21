package com.shm.dim.lab_16;

import android.animation.AnimatorSet;
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

    private TextView mTimer;
    private ImageView mImage1, mImage2;
    public BallFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ball, container, false);

        mTimer = view.findViewById(R.id.timer);
        mImage1 = view.findViewById(R.id.image_1);
        mImage2 = view.findViewById(R.id.image_2);

        startAnimation(3000);
        startTimer(3000, 1000);

        return view;
    }

    private void startTimer(int duration, int step) {

        final String FORMAT = "%02d:%02d:%02d";

        new CountDownTimer(duration, step) {
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

    private void startAnimation(int duration) {
        ObjectAnimator animation1 = new ObjectAnimator()
                .ofFloat(mImage1, "rotation", 0f, 360f)
                .setDuration(duration);
        ObjectAnimator animation2 = new ObjectAnimator()
                .ofFloat(mImage2, "rotation", 360f, 0f)
                .setDuration(duration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animation1, animation2);
        animatorSet.start();
    }
}