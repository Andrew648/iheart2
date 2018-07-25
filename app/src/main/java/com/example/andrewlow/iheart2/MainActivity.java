package com.example.andrewlow.iheart2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    int[] images = {R.drawable.lecture,R.drawable.family,R.drawable.chapel,R.drawable.handshake,R.drawable.individual,R.drawable.destiny,R.drawable.market};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlClass sql = new sqlClass(this);
        SQLiteDatabase db = sql.getReadableDatabase();

        String[]projection={sqlClass.Table1.column};
        Cursor c = db.query(sqlClass.Table1.TABLE_NAME,null,null,null,null,null,null);


        listView = findViewById(R.id.list);
        ArrayList<String> items = new ArrayList<>();
        //Toast.makeText(getApplicationContext(),""+c.getCount(),Toast.LENGTH_SHORT).show();
        while(c.moveToNext()){
            String cr = c.getString(c.getColumnIndex("Title"));
            items.add(cr);

        }
        //items.add("God's people");
        /*items.add("Family");
        items.add("Church Life");
        items.add("Disciples - corporate");
        items.add("Disciples - individual");
        items.add("Disciples - Calling/Destiny");
        items.add("Marketplace Life");*/

        CustomAdapter adapter;
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        adapter = new CustomAdapter(MainActivity.this,items,images,c.getCount());
        //Toast.makeText(getApplicationContext(),""+c.getCount(),Toast.LENGTH_SHORT).show();
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
}
