package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
                readingIntent.putExtra("username", username);
                startActivity(readingIntent);
            }
        });

        btnQuiz = findViewById(R.id.guizBtn);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quizIntent = new Intent(Modes.this, GameMode.class);
                quizIntent.putExtra("username", username);
                startActivity(quizIntent);
            }
        });

        btnFinal = findViewById(R.id.finalBtn);
        //Guest will not allow to play the final Quiz. Need to have an account to play
        if (!username.equals("guest")) {
            //If any mode finished set enabled
            Intent finalQuizIntent = getIntent();
            final String finishedMode = finalQuizIntent.getStringExtra("finishedMode");
            if (finishedMode != null) {
                Toast.makeText(this, finishedMode + " finished", Toast.LENGTH_SHORT).show();
                if (finishedMode.equals("StudyMode") || finishedMode.equals("GameMode")) {
                    btnFinal.setEnabled(true);
                }
            }

            btnFinal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent quizIntent = new Intent(Modes.this, FinalQuiz.class);
                    quizIntent.putExtra("username", username);
                    quizIntent.putExtra("completedMode", finishedMode);
                    startActivity(quizIntent);
                }
            });
        }
    }
}
