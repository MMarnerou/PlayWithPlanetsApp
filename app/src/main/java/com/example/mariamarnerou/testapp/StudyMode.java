package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class StudyMode extends AppCompatActivity {
    public String fileName = "planites.html";
    WebView webView;
    Button btnRead;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_mode);

        Intent usernameIntent = getIntent();
        username = usernameIntent.getStringExtra("username");

        btnRead = findViewById(R.id.readBtn);
        if (username != null) {
            btnRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent finalQuiz = new Intent(StudyMode.this, Modes.class);
                    finalQuiz.putExtra("finishedMode", "StudyMode");
                    finalQuiz.putExtra("username", username);
                    startActivity(finalQuiz);
                }
            });
        }

        webView = findViewById(R.id.bookContent);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/" + fileName);
    }
}
