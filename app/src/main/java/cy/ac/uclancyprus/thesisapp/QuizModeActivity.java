package cy.ac.uclancyprus.thesisapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class QuizModeActivity extends AppCompatActivity {

    ImageButton button1Btn, button2Btn, button3Btn, button4Btn, button5Btn, button6Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_mode);

        button1Btn = (ImageButton) findViewById(R.id.quiz1);
        button2Btn = (ImageButton) findViewById(R.id.quiz2);
        button3Btn = (ImageButton) findViewById(R.id.quiz3);
        button4Btn = (ImageButton) findViewById(R.id.quiz4);
        button5Btn = (ImageButton) findViewById(R.id.quiz5);
        button6Btn = (ImageButton) findViewById(R.id.quiz6);

        //Start Quiz 1
        button1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(QuizModeActivity.this, QuizActivity.class);
                startActivity(quizIntent);
            }
        });
        //End Quiz 1

        //Start Quiz 2
        button2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(QuizModeActivity.this, QuizActivity.class);
                startActivity(quizIntent);
            }
        });
        //End Quiz 2
        //Start Quiz 3
        button3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(QuizModeActivity.this, QuizActivity.class);
                startActivity(quizIntent);
            }
        });
        //End Quiz 3
    }
}
