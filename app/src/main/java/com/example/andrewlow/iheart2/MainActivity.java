package com.example.andrewlow.iheart2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        ArrayList<String> items = new ArrayList<>();
        items.add("Lesson 1");
        items.add("Lesson 2");
        items.add("Lesson 3");

        CustomAdapter adapter;
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.,items);
        adapter = new CustomAdapter(MainActivity.this,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String f = ((TextView)view.findViewById(R.id.text)).getText().toString();
                Toast.makeText(getApplicationContext(),f,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
