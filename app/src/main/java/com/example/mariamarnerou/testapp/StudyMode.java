package com.example.mariamarnerou.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class StudyMode extends AppCompatActivity {
    public String fileName = "planites.html";
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_mode);

        webView = findViewById(R.id.bookContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/" + fileName);
//        try {
//            InputStream book = getAssets().open("Quiz1.txt");
//            int size = book.available();
//
//            //Read the entire asset into a local byte buffer
//            byte[] buffer = new byte[size];
//            book.read(buffer);
//            book.close();
//
//            String text = new String(buffer);
//            bookContent.setText(text);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //End Slide Page
    }
}
