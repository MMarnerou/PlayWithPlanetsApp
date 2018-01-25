package com.example.mariamarnerou.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class StudyMode extends AppCompatActivity {
    TextView bookContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_mode);

        //Start Slide Page
        bookContent = (TextView) findViewById(R.id.bookContent);
        try {
            InputStream book = getAssets().open("Quiz1.txt");
            int size = book.available();

            //Read the entire asset into a local byte buffer
            byte[] buffer = new byte[size];
            book.read(buffer);
            book.close();

            String text = new String(buffer);
            bookContent.setText(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //End Slide Page
    }
}
