package com.example.andrewlow.iheart2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BibleDialog extends AppCompatDialogFragment {

    private String verseSource;
    private String verseTitle;



    public  BibleDialog(String verseSource,String verseTitle){
        this.verseSource = verseSource;
        this.verseTitle = verseTitle;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        URL url;
        HttpURLConnection urlConnection = null;
        String webContent = null;
        try {
            url = new URL(verseSource);
            urlConnection = (HttpURLConnection)url.openConnection();
            int code = urlConnection.getResponseCode();
            if (code==200){
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line;

                while ((line = bufferedReader.readLine()) != null)
                    webContent += line;
                in.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        Document doc = Jsoup.parse(webContent);
        Element link = doc.select("p").first();

        String theVerse=link.text();
        AlertDialog.Builder verse = new AlertDialog.Builder(getActivity());
        verse.setTitle(verseTitle)
                .setMessage(theVerse);
        return verse.create();
    }

}
