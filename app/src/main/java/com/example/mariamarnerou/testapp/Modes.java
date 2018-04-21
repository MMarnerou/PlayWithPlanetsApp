package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Modes extends AppCompatActivity {
    TextView txtUser;
    String username;
    ImageButton btnReading, btnQuiz;
    Button btnFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);

        //Start Welcome label
        Intent usernameIntent = getIntent();
        username = usernameIntent.getStringExtra("username");

        if (username == null) {
            username = "guest";
        }

        txtUser = findViewById(R.id.welcome);
        txtUser.setText("Καλοσώρισες " + username);
        // End Welcome label

        btnReading = findViewById(R.id.readingBtn);
        btnReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readingIntent = new Intent(Modes.this, StudyMode.class);
                startActivity(readingIntent);
            }
        });

        btnQuiz = findViewById(R.id.guizBtn);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(Modes.this, GameMode.class);
                startActivity(quizIntent);
            }
        });

        btnFinal = findViewById(R.id.finalBtn);
        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(Modes.this, FinalQuiz.class);
                startActivity(quizIntent);
            }
        });
    }
}
