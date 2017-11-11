package cy.ac.uclancyprus.thesisapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    QuestionLibrary questionLibrary = new QuestionLibrary();
    TextView questionTxt, questionID;
    String answer;
    int defColor = Color.LTGRAY;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn;
    Integer questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        answer1Btn = (Button) findViewById(R.id.answer1);
        answer2Btn = (Button) findViewById(R.id.answer2);
        answer3Btn = (Button) findViewById(R.id.answer3);
        answer4Btn = (Button) findViewById(R.id.answer4);
        questionTxt = (TextView) findViewById(R.id.questionTXT);
        questionID = (TextView) findViewById(R.id.questionID);
        setDefColorButtons();

        //Take a random question
        questionId = 1;

        //Display Question & Choices
        updateQuestions(questionId);

        //Choice1's Button listener
        answer1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefColorButtons();
                if ((questionLibrary.getChoice1(questionId)).equals(answer)) {
                    answer1Btn.setBackgroundColor(Color.GREEN);
                } else {
                    answer1Btn.setBackgroundColor(Color.RED);
                }
            }
        });
        //Choice2's Button listener
        answer2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefColorButtons();
                if ((questionLibrary.getChoice2(questionId)).equals(answer)) {
                    answer2Btn.setBackgroundColor(Color.GREEN);

                } else {
                    answer2Btn.setBackgroundColor(Color.RED);
                }
            }
        });
        //Choice3's Button listener
        answer3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefColorButtons();
                if ((questionLibrary.getChoice3(questionId)).equals(answer)) {
                    answer3Btn.setBackgroundColor(Color.GREEN);
                } else {
                    answer3Btn.setBackgroundColor(Color.RED);
                }
            }
        });
        //Choice4's Button listener
        answer4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDefColorButtons();
                if ((questionLibrary.getChoice4(questionId)).equals(answer)) {
                    answer4Btn.setBackgroundColor(Color.GREEN);
                } else {
                    answer4Btn.setBackgroundColor(Color.RED);
                }
            }
        });
    }

    private void updateQuestions(int id) {
        // Display question to the Text View
        questionID.setText("QUESTION: " + id);
        questionTxt.setText(questionLibrary.getQuestion(id));
        // Display Answers to the screen
        answer1Btn.setText(questionLibrary.getChoice1(id));
        answer2Btn.setText(questionLibrary.getChoice2(id));
        answer3Btn.setText(questionLibrary.getChoice3(id));
        answer4Btn.setText(questionLibrary.getChoice4(id));
        // Get the right answer from QuestionBank
        answer = questionLibrary.getCorrectAnswer(id);
    }

    private void setDefColorButtons() {
        answer1Btn.setBackgroundColor(defColor);
        answer2Btn.setBackgroundColor(defColor);
        answer3Btn.setBackgroundColor(defColor);
        answer4Btn.setBackgroundColor(defColor);

    }
}

