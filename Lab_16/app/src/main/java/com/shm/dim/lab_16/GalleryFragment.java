package com.shm.dim.lab_16;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryFragment extends Fragment {

    @SuppressWarnings("deprecation")
    Gallery mGallery;
    Integer[] GalleryImagesList = {
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven
    };
    ImageView mImage;

    public GalleryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        mImage = view.findViewById(R.id.gallery_image);
        mImage.setImageResource(R.drawable.one);

        mGallery = view.findViewById(R.id.gallery);
        mGallery.setAdapter(new ImageAdapter(GalleryFragment.this.getContext()));
        mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mImage.setImageResource(GalleryImagesList[position]);
            }
        });

        return view;
    }


    private class ImageAdapter extends BaseAdapter {
        Context context;

        public ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return GalleryImagesList.length;
        }

        @Override
        public Object getItem(int position) {
            return GalleryImagesList[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(this.context);

            imageView.setImageResource(GalleryImagesList[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(400, 300));
            imageView.setPadding(50, 0, 50, 0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            return imageView;
        }
    }
}