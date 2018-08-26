package com.example.andrewlow.iheart2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class lessonP extends AppCompatActivity {

    TextView lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_p);

        TextView t = findViewById(android.R.id.text1);
        lesson = findViewById(R.id.content);
        Intent it = getIntent();
        String tt =  it.getStringExtra("title");
        t.setText(tt);
        CharSequence text;
        if(tt.equals("Preparation")){
            int topicId = this.getResources().getIdentifier("lesson","string",this.getPackageName());

            text = Html.fromHtml(getString(topicId));
           //lesson.setText(Html.fromHtml(getString(R.string.lesson)));
        }else{
            text = Html.fromHtml(getString(R.string.bold));
        }
        //CharSequence text = Html.fromHtml(getString(R.string.bold));
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

        /*((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.title_template_step, mPageNumber + 1));*/
    }

    private void OpenDialog(String verse, String verseName) {
        BibleDialog bible = new BibleDialog(verse,verseName);
        bible.show(getSupportFragmentManager(),"Bible verse");
    }

}
