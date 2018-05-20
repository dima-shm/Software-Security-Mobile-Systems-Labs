package com.shm.dim.lab_16;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickGallery(View view) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.gallery_fragment, new GalleryFragment())
                .commit();
    }

    public void onClickBall(View view) {
        getFragmentManager()
                .beginTransaction()
                .add(R.id.ball_fragment, new BallFragment())
                .commit();
    }
}