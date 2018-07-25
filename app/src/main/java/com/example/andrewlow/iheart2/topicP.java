package com.example.andrewlow.iheart2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class topicP extends AppCompatActivity {

    ArrayList<String>topic = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_p);

        ListView lt = findViewById(R.id.list);

        Intent it = getIntent();
        String tt =  it.getStringExtra("title");
        if(tt.equals("Gods people")){
            topic.add(getString(R.string.god));
            topic.add(getString(R.string.god2));
            topic.add(getString(R.string.god3));
        }

        if(tt.equals("Family Life")){
            topic.add(getString(R.string.family));
            topic.add(getString(R.string.family2));
            topic.add(getString(R.string.family3));
            topic.add(getString(R.string.family4));
            topic.add(getString(R.string.family5));
            topic.add(getString(R.string.family6));
            topic.add(getString(R.string.family7));
        }

        if(tt.equals("Church Life")){
            topic.add(getString(R.string.church));
            topic.add(getString(R.string.church2));
            topic.add(getString(R.string.church3));
            topic.add(getString(R.string.church4));
            topic.add(getString(R.string.church5));
            topic.add(getString(R.string.church6));
            topic.add(getString(R.string.church7));
            topic.add(getString(R.string.church8));
            topic.add(getString(R.string.church9));
            topic.add(getString(R.string.church10));
            topic.add(getString(R.string.church11));
            topic.add(getString(R.string.church12));
            topic.add(getString(R.string.church13));
            topic.add(getString(R.string.church14));
            topic.add(getString(R.string.church15));
        }

        if(tt.equals("Disciples - corporate")){
            topic.add(getString(R.string.corporate));
            topic.add(getString(R.string.corporate2));
            topic.add(getString(R.string.corporate3));
            topic.add(getString(R.string.corporate4));
            topic.add(getString(R.string.corporate5));
            topic.add(getString(R.string.corporate6));
            topic.add(getString(R.string.corporate7));
        }

        if(tt.equals("Disciples - individual")){
            topic.add(getString(R.string.individual));
            topic.add(getString(R.string.individual2));
            topic.add(getString(R.string.individual3));
            topic.add(getString(R.string.individual4));
            topic.add(getString(R.string.individual5));
            topic.add(getString(R.string.individual6));
            topic.add(getString(R.string.individual7));
            topic.add(getString(R.string.individual8));
        }

        if(tt.equals("Disciples - Calling/Destiny")){
            topic.add(getString(R.string.call));
            topic.add(getString(R.string.call2));
            topic.add(getString(R.string.call3));
            topic.add(getString(R.string.call4));
            topic.add(getString(R.string.call5));
            topic.add(getString(R.string.call6));

        }

        if(tt.equals("Marketplace Life")){
            topic.add(getString(R.string.market));
            topic.add(getString(R.string.market2));
            topic.add(getString(R.string.market3));
            topic.add(getString(R.string.market4));
            topic.add(getString(R.string.market5));
            topic.add(getString(R.string.market6));
            topic.add(getString(R.string.market7));
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.simple_list,R.id.text,topic
        );
        lt.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        topic.clear();
        super.onBackPressed();
    }
}
