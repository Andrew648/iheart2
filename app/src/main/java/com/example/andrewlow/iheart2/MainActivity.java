package com.example.andrewlow.iheart2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private AdView mAdView;

    //int[] images = {R.drawable.baby,R.drawable.lecture,R.drawable.family,R.drawable.chapel,R.drawable.handshake,R.drawable.individual,R.drawable.destiny,R.drawable.market};
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        ArrayList<String> items = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.title)));
        ArrayList<String> numless = countLesson(items);
        ArrayList<Integer> icon = imageID(items);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        CustomAdapter adapter;
        adapter = new CustomAdapter(MainActivity.this,items,icon,numless);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String f = ((TextView)view.findViewById(R.id.text)).getText().toString();

                Intent it = new Intent(getApplicationContext(),topicP.class);
                it.putExtra("title",f);
                startActivity(it);
            }
        });
    }

    private ArrayList<String> countLesson(ArrayList category){
        ArrayList <String> list = new ArrayList<>();
        for(int i = 0;i<category.size();i++){
            String topicID = category.get(i).toString().replace(" ","_");
            int id = getResources().getIdentifier(topicID,"array",getPackageName());
            int size = Arrays.asList(getResources().getStringArray(id)).size();
            list.add( size + " lessons");
        }
        return list;
    }

    private ArrayList<Integer> imageID(ArrayList category){
        ArrayList<Integer> pic = new ArrayList<>();
        for(int i=0;i<category.size();i++){
            String topicID = category.get(i).toString().replace(" ","_");
            topicID = topicID.toLowerCase();
            int id = getResources().getIdentifier(topicID,"drawable",getPackageName());
            pic.add(id);
        }
        return pic;
    }
}
