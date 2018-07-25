package com.example.andrewlow.iheart2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

public class lessonP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_p);

        TextView t = findViewById(android.R.id.text1);
        TextView lesson = findViewById(R.id.content);
        Intent it = getIntent();
        String tt =  it.getStringExtra("title");

        t.setText(tt);
        lesson.setText(Html.fromHtml(getString(R.string.lesson)));
        /*((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.title_template_step, mPageNumber + 1));*/
    }
}
