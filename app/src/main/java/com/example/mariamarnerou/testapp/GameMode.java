package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mariamarnerou.testapp.model.Quiz;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GameMode extends AppCompatActivity {

    private Quiz quiz;
    ImageButton plutoBtn, neptuneBtn, uranusBtn, saturnBtn, jupiterBtn, marsBtn, earthBtn, venusBtn, mercuryBtn, sunBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        final Integer[] planet = new Integer[1];

        plutoBtn = findViewById(R.id.pluto);
        neptuneBtn = findViewById(R.id.neptune);
        uranusBtn = findViewById(R.id.uranus);
        saturnBtn = findViewById(R.id.saturn);
        jupiterBtn = findViewById(R.id.jupiter);
        marsBtn = findViewById(R.id.mars);
        venusBtn = findViewById(R.id.venus);
        earthBtn = findViewById(R.id.earth);
        mercuryBtn = findViewById(R.id.mercury);
        //sunBtn = (ImageButton) findViewById(R.id.sun);

        try {
            final InputStream inputStream = getAssets().open("questions.json");
            quiz = new Gson().fromJson(new InputStreamReader(inputStream), Quiz.class);
            Log.d("planets", quiz.toString());

        } catch (IOException ioe) {
            Log.e("planets", ioe.getMessage(), ioe);
        }

        //Start Quiz 1

        plutoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planet[0] = 0;
                Intent quizIntent1 = new Intent(GameMode.this, planetQuiz.class);
                quizIntent1.putExtra("planet", planet[0]);
                startActivity(quizIntent1);
            }
        });
        //End Quiz 1

        //Start Quiz 2
        neptuneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planet[0] = 1;
                Intent quizIntent2 = new Intent(GameMode.this, planetQuiz.class);
                quizIntent2.putExtra("planet", planet[0]);
                startActivity(quizIntent2);
            }
        });
        //End Quiz 2

        //Start Quiz 3
        uranusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planet[0] = 2;
                Intent quizIntent3 = new Intent(GameMode.this, planetQuiz.class);
                quizIntent3.putExtra("planet", planet[0]);
                startActivity(quizIntent3);
            }
        });
        //End Quiz 3

        //Start Quiz 4
        saturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planet[0] = 3;
                Intent quizIntent4 = new Intent(GameMode.this, planetQuiz.class);
                quizIntent4.putExtra("planet", planet[0]);
                startActivity(quizIntent4);
            }
        });
        //End Quiz 4

        //Start Quiz 5
        jupiterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planet[0] = 4;
                Intent quizIntent5 = new Intent(GameMode.this, planetQuiz.class);
                quizIntent5.putExtra("planet", planet[0]);
                startActivity(quizIntent5);
            }
        });
        //End Quiz 5

        // Start Quiz 6
        marsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planet[0] = 5;
                Intent quizIntent6 = new Intent(GameMode.this, planetQuiz.class);
                quizIntent6.putExtra("planet", planet[0]);
                startActivity(quizIntent6);
            }
        });
        //End Quiz 6

        // Start Quiz 7
        venusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planet[0] = 6;
                Intent quizIntent7 = new Intent(GameMode.this, planetQuiz.class);
                quizIntent7.putExtra("planet", planet[0]);
                startActivity(quizIntent7);
            }
        });
        //End Quiz 7

        // Start Quiz 8
        earthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planet[0] = 7;
                Intent quizIntent8 = new Intent(GameMode.this, planetQuiz.class);
                quizIntent8.putExtra("planet", planet[0]);
                startActivity(quizIntent8);
            }
        });
        //End Quiz 8
    }

    public void quiz(View view) {
        final Intent intent = new Intent(GameMode.this, planetQuiz.class);
        if (view == plutoBtn) {
            Toast.makeText(this, "This is " + quiz.getPlanet("Pluto"), Toast.LENGTH_SHORT).show();
            intent.putExtra("planet", quiz.getPlanet("Pluto"));
        } else if (view == neptuneBtn) {
            intent.putExtra("planet", "Neptune");
        } else if (view == uranusBtn) {
            intent.putExtra("planet", "Uranus");
        } else if (view == mercuryBtn) {
            intent.putExtra("planet", "Mercury");
        } else if (view == marsBtn) {
            intent.putExtra("planet", "Mars");
        } else if (view == jupiterBtn) {
            intent.putExtra("planet", "Jupiter");
        } else if (view == earthBtn) {
            intent.putExtra("planet", "Earth");
        } else if (view == saturnBtn) {
            intent.putExtra("planet", "Saturn");
        } else if (view == venusBtn) {
            intent.putExtra("planet", "Venus");
        }
        startActivity(intent);

    }
}
