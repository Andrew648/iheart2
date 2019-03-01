package com.example.andrewlow.iheart2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

public class lessonP extends AppCompatActivity {

    TextView lesson;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_p);

        TextView t = findViewById(android.R.id.text1);
        lesson = findViewById(R.id.content);
        toolbar = findViewById(R.id.toolbar2);

        Intent it = getIntent();
        String tt =  it.getStringExtra("title");
        t.setText(tt);
        CharSequence text;
        String title = tt.replace(" ","_");
        title = title.replaceAll("[\\-,’=>?“”]","");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(getString(R.string.app_name));
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int topicId = this.getResources().getIdentifier(title,"string",this.getPackageName());

        if (topicId != 0){
            text = Html.fromHtml(getString(topicId));
        }else{
           text = Html.fromHtml(getString(R.string.bold));
        }

        final SpannableStringBuilder span = new SpannableStringBuilder(text);
        URLSpan[] urlSpan = span.getSpans(0,text.length(),URLSpan.class);
        for (final URLSpan s : urlSpan){
            final int start = span.getSpanStart(s);
            final int end = span.getSpanEnd(s);

            span.removeSpan(s);
            span.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    String command = s.getURL();
                    String verseName = span.subSequence(start,end).toString();
                    if (command.equals("back")){
                        finish();
                    }else {
                        OpenDialog(s.getURL(),verseName);
                    }
                }

            },start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        lesson.setText(span);
        lesson.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void OpenDialog(String verse, String verseName) {
        BibleDialog bible = null;
        try {
            bible = new BibleDialog(verse,verseName);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        bible.show(getSupportFragmentManager(),"Bible verse");
    }



}
