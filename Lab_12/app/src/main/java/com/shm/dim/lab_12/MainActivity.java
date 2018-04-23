package com.shm.dim.lab_12;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private int firstSoundID, secondSoundID;
    private int firstStreamID, secondStreamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        firstSoundID = mSoundPool.load(this, R.raw.sound1, 1);
        secondSoundID = mSoundPool.load(this, R.raw.sound2, 1);
    }


    public void playOnClick(View v) {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float leftVolume = curVolume / maxVolume;
        float rightVolume = curVolume / maxVolume;
        int priority = 1;
        int noLoop = 0;
        float normalPlaybackRate = 1f;

        firstStreamID = mSoundPool.play(firstSoundID, leftVolume, rightVolume, priority, noLoop,
                normalPlaybackRate);
        secondStreamID = mSoundPool.play(secondSoundID, leftVolume, rightVolume, priority, noLoop,
                normalPlaybackRate);
    }


    public void pauseOnClick(View v) {
        mSoundPool.pause(firstStreamID);
        mSoundPool.pause(secondStreamID);
    }
}
