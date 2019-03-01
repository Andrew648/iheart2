package com.example.andrewlow.iheart2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressLint("ValidFragment")
public class BibleDialog extends AppCompatDialogFragment {

    private XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
    private XmlPullParser myparser = xmlFactoryObject.newPullParser();

    private String verseSource;
    private String verseTitle;


    public  BibleDialog(String verseSource,String verseTitle) throws XmlPullParserException {
        this.verseSource = verseSource;
        this.verseTitle = verseTitle;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String ver = "";
        String []splitter = verseSource.split("_");
        String bnumber = splitter[0],cnumber = splitter[1],vnumber=splitter[2];
        try {
            InputStream stream = getActivity().getAssets().open("bible_english.xml");
            myparser.setInput(stream,null);
            int event = myparser.getEventType();
            String book = null, chapter= null, verse = null;
            int start, end = 0, pointer = 0;
            while(event!=XmlPullParser.END_DOCUMENT){
                String name = myparser.getName();
                Boolean c = false;
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(name.equals("BIBLEBOOK")){
                            if(myparser.getAttributeValue(null,"bnumber").equals(bnumber)) {
                                book = name;
                            }
                        }else if(name.equals("CHAPTER") && book != null){
                            if(myparser.getAttributeValue(null,"cnumber").equals(cnumber)){
                                chapter = name;
                            }
                        }else if(name.equals("VERS") && chapter != null){
                            if(vnumber.contains("-")){
                                start = Integer.parseInt(vnumber.substring(0,vnumber.indexOf("-")));
                                end = Integer.parseInt(vnumber.substring(vnumber.indexOf("-")+1));
                                if(pointer<=0){
                                    pointer = start;
                                }
                                if(myparser.getAttributeValue(null,"vnumber").equals(Integer.toString(pointer))){
                                    verse = name;
                                }
                            }else{
                                if(myparser.getAttributeValue(null,"vnumber").equals(vnumber)){
                                    verse = name;
                                }
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(verse != null){
                            if(vnumber.contains("-")){
                                if (myparser.isWhitespace()){
                                    break;
                                }else if(pointer != end){
                                    ver += pointer + " " + myparser.getText();
                                    pointer++;
                                }else {
                                    ver += pointer + " " + myparser.getText();
                                    c = true;
                                }
                            }else{
                                ver = myparser.getText();
                                c = true;
                            }
                        }
                        break;
                }

                if(c == true)
                    break;

                event = myparser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        AlertDialog.Builder verse = new AlertDialog.Builder(getActivity());
        verse.setTitle(verseTitle)
                .setMessage(ver)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        return verse.create();
    }




}
