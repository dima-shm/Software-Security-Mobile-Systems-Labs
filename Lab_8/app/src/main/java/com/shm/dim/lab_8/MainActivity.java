package com.shm.dim.lab_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list);

        String[] values = { "UK", "USA", "France", "Germany", "Belarus", "Spain" };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int position = arg2;
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                switch(position)
                {
                    case 0:
                        intent.putExtra("COUNTRY", new String[] {"London", "Brighton", "Manchester", "Liverpool"});
                        break;
                    case 1:
                        intent.putExtra("COUNTRY", new String[] {"New York", "Los Angeles", "Chicago", "Phoenix"});
                        break;
                    case 2:
                        intent.putExtra("COUNTRY", new String[] {"Paris", "Marseille", "Lyon", "Nice"});
                        break;
                    case 3:
                        intent.putExtra("COUNTRY", new String[] {"Berlin", "München", "Mecklenburg", "Düsseldorf"});
                        break;
                    case 4:
                        intent.putExtra("COUNTRY", new String[] {"Minsk", "Vitebsk", "Gomel", "Zhodino"});
                        break;
                    case 5:
                        intent.putExtra("COUNTRY", new String[] {"Madrid", "Barcelona", "Valencia", "Palma de Mallorca"});
                        break;
                }
                startActivity(intent);
            }
        });
    }

}