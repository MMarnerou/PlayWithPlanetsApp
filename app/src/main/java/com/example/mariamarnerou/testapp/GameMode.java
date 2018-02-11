package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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
        // todo your images are nice but the text in it os too small to be readable
        // todo make sure that any text/images/sounds you use are properly cited and that you only use free resources from the public domain
        // todo I see that when I play one of the planets (e.g. Neptune) when I click 'next question' nothing happens.

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
                planetChoice(plutoBtn);
            }
        });
        //End Quiz 1

        //Start Quiz 2
        neptuneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetChoice(neptuneBtn);
            }
        });
        //End Quiz 2

        //Start Quiz 3
        uranusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetChoice(uranusBtn);
            }
        });
        //End Quiz 3

        //Start Quiz 4
        saturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetChoice(saturnBtn);
            }
        });
        //End Quiz 4

        //Start Quiz 5
        jupiterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetChoice(jupiterBtn);
            }
        });
        //End Quiz 5

        // Start Quiz 6
        marsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetChoice(marsBtn);
            }
        });
        //End Quiz 6

        // Start Quiz 7
        venusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetChoice(venusBtn);            }
        });
        //End Quiz 7

        // Start Quiz 8
        earthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planetChoice(earthBtn);            }
        });
        //End Quiz 8
    }

    public void planetChoice(View view) {
        Intent quiz_intent = new Intent(this, PlanetQuiz.class);
        if (view == plutoBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Pluto"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Pluto");
        } else if (view == neptuneBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Neptune"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Neptune");
        } else if (view == uranusBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Uranus"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Uranus");
        } else if (view == mercuryBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Mercury"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Mercury");
        } else if (view == marsBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Mars"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Mars");
        } else if (view == jupiterBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Jupiter"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Jupiter");
        } else if (view == earthBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Earth"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Earth");
        } else if (view == saturnBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Saturn"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Saturn");
        } else if (view == venusBtn) {
//            Toast.makeText(this, "This is " + quiz.getPlanet("Venus"), Toast.LENGTH_SHORT).show();
            quiz_intent.putExtra("planet", "Venus");
        }
        if (view!=null) {
        startActivity(quiz_intent);
        }
    }
}
