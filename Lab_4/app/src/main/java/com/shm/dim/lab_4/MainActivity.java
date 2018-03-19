package com.shm.dim.lab_4;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImage1,
            mImage2;
    
    private EditText mAnimationDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage1 = findViewById(R.id.image_1);
        mImage2 = findViewById(R.id.image_2);

        mAnimationDuration = findViewById(R.id.animation_duration);
    }


    private int getAnimationDuration() {
        if(!mAnimationDuration.getText().toString().equals("")) {
            return new Integer(mAnimationDuration.getText().toString());
        } else {
            return 1500;
        }
    }

    public void startAsynchAnimation_onClick(View view) {
        int animDuration = getAnimationDuration();

        ObjectAnimator animation1 = new ObjectAnimator()
                .ofFloat(mImage1, "rotation", 0f, 360f)
                .setDuration(animDuration);
        animation1.start();

        ObjectAnimator animation2 = new ObjectAnimator()
                .ofFloat(mImage2, "rotation", 0f, 360f)
                .setDuration(animDuration);
        animation2.setStartDelay(1000);
        animation2.start();
    }

    public void startSynchAnimation_onClick(View view) {
        int animDuration = getAnimationDuration();

        ObjectAnimator animation1 = new ObjectAnimator()
                .ofFloat(mImage1, "rotation", 0f, 360f)
                .setDuration(animDuration);
        ObjectAnimator animation2 = new ObjectAnimator()
                .ofFloat(mImage2, "rotation", 0f, 360f)
                .setDuration(animDuration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animation1, animation2);
        animatorSet.start();
    }
}