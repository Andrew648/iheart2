package com.example.andrewlow.iheart2;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class topicP extends Activity {

    ArrayList<String>topic = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_p);

        ListView lt = findViewById(R.id.list);
        ImageView iv = findViewById(R.id.imageView2);
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent it = getIntent();
        String tt =  it.getStringExtra("title");
        String listTitle = tt.replace(" ","_");
        int id = getResources().getIdentifier(listTitle,"array",getPackageName());
        topic = new ArrayList<>(Arrays.asList(getResources().getStringArray(id)));
        //iv.setImageResource(R.drawable.people);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(getString(R.string.app_name));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.simple_list,R.id.text,topic);
        lt.setAdapter(adapter);
        lt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String f = ((TextView)view.findViewById(R.id.text)).getText().toString();
               // Toast.makeText(getApplicationContext(),f,Toast.LENGTH_SHORT).show();
                Intent it = new Intent(getApplicationContext(),lessonP.class);
                it.putExtra("title",f);
                startActivity(it);
            }
        });
    }

    @Override
    public void onBackPressed() {
        topic.clear();
        super.onBackPressed();
    }
}
