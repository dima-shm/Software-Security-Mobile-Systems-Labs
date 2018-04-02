package com.shm.dim.lab_8;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mListView = findViewById(R.id.list);

        Intent intent = getIntent();
        String country = intent.getStringExtra("COUNTRY");
        String[] values = new String[4];

        switch (country) {
            case "UK":
                values = new String[] {"London", "Brighton", "Manchester", "Liverpool"};
                break;
            case "USA":
                values = new String[] {"New York", "Los Angeles", "Chicago", "Phoenix"};
                break;
            case "France":
                values = new String[] {"Paris", "Marseille", "Lyon", "Nice"};
                break;
            case "Germany":
                values = new String[] {"Berlin", "München", "Mecklenburg", "Düsseldorf"};
                break;
            case "Belarus":
                values = new String[] {"Minsk", "Vitebsk", "Gomel", "Zhodino"};
                break;
            case "Spain":
                values =  new String[] {"Madrid", "Barcelona", "Valencia", "Palma de Mallorca"};
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values){
            @Override
            public View getView(int position, View contentView, ViewGroup parent) {
                View view = super.getView(position, contentView, parent);

                Random r = new Random();
                int color = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));

                TextView t = view.findViewById(android.R.id.text1);
                t.setTextColor(color);

                int color1 = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
                view.setBackgroundColor(color1);
                return view;
            }
        };
        mListView.setAdapter(adapter);

        LayoutAnimationController controller = AnimationUtils
                .loadLayoutAnimation(this, R.anim.list_layout_controller);
        mListView.setLayoutAnimation(controller);
    }
}
