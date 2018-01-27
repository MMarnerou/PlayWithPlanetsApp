package cy.ac.uclancyprus.thesisapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import cy.ac.uclancyprus.thesisapp.model.Quiz;

public class QuizActivity extends AppCompatActivity {

    QuestionLibrary questionLibrary = new QuestionLibrary();
    TextView questionTxt, questionID;
    String answer;
    //    int defColor = Color.LTGRAY;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn;
    Integer questionId;

    public static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
            System.out.print("line = " + line + "sb.append(line).append" + sb.append(line).append("\n"));
        }
        reader.close();
        return sb.toString();
    }

//        //Take a random question
//        questionId = 1;
//
//
////        //Display Planet & Choices
//        updateQuestions(questionId);
////
////        //Choice1's Button listener
//        answer1Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ((questionLibrary.getChoice1(questionId)).equals(answer)) {
//                    answer1Btn.setBackgroundColor(Color.GREEN);
//                } else {
//                    answer1Btn.setBackgroundColor(Color.RED);
//                }
//            }
//        });
//        //Choice2's Button listener
//        answer2Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ((questionLibrary.getChoice2(questionId)).equals(answer)) {
//                    answer2Btn.setBackgroundColor(Color.GREEN);
//
//                } else {
//                    answer2Btn.setBackgroundColor(Color.RED);
//                }
//            }
//        });
//        //Choice3's Button listener
//        answer3Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ((questionLibrary.getChoice3(questionId)).equals(answer)) {
//                    answer3Btn.setBackgroundColor(Color.GREEN);
//                } else {
//                    answer3Btn.setBackgroundColor(Color.RED);
//                }
//            }
//        });
//        //Choice4's Button listener
//        answer4Btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ((questionLibrary.getChoice4(questionId)).equals(answer)) {
//                    answer4Btn.setBackgroundColor(Color.GREEN);
//                } else {
//                    answer4Btn.setBackgroundColor(Color.RED);
//                }
//            }
//        });
//    }
//
//    private void updateQuestions(int id) {
//        // Display question to the Text View
//        questionID.setText("QUESTION: " + id);
//        questionTxt.setText(questionLibrary.getQuestion(id));
//        // Display Answers to the screen
//        answer1Btn.setText(questionLibrary.getChoice1(id));
//        answer2Btn.setText(questionLibrary.getChoice2(id));
//        answer3Btn.setText(questionLibrary.getChoice3(id));
//        answer4Btn.setText(questionLibrary.getChoice4(id));
//        // Get the right answer from QuestionBank
//        answer = questionLibrary.getCorrectAnswer(id);
//    }
//
////    private void setDefColorButtons() {
////        answer1Btn.setBackgroundColor(defColor);
////        answer2Btn.setBackgroundColor(defColor);
////        answer3Btn.setBackgroundColor(defColor);
////        answer4Btn.setBackgroundColor(defColor);
////
////    }

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

        try {
            final InputStream inputStream = getAssets().open("questions.json");
            final Quiz quiz = new Gson().fromJson(new InputStreamReader(inputStream), Quiz.class);
            Log.d("planets", quiz.toString());
        } catch (IOException ioe) {
            Log.e("planets", ioe.getMessage(), ioe);
        }

//        JSONObject root;
//        try {
//            String json = convertStreamToString(getAssets().open("questions.json"));
//            root = new JSONObject(json);
//            JSONObject questions = root.getJSONObject("questions");
////            Toast.makeText(this, "planet: " + questions, Toast.LENGTH_SHORT).show();
//
//            for (Iterator iterator = questions.keys(); iterator.hasNext(); ) {
//                String planet = iterator.next().toString();
//                Toast.makeText(this, "planet: " + planet, Toast.LENGTH_SHORT).show();
//
//                JSONObject planetJsonObject = questions.getJSONObject(planet);
//                String welcome = planetJsonObject.getString("Welcome");
//                JSONArray planetQuestions = planetJsonObject.getJSONArray("questions");
//                for (int i = 0; i < planetQuestions.length(); i++) {
//                    JSONObject question = planetQuestions.getJSONObject(i);
//                    String q = question.getString("q");
//                    JSONArray aJsonArray = question.getJSONArray("a");
//                    String[] a = new String[aJsonArray.length()];
//                    for (int j = 0; j < aJsonArray.length(); j++) {
//                        a[0] = aJsonArray.getString(j);
//                    }
//                    int c = question.getInt("c");
//                    Planet qq = new Planet(q, a, c);
//                    Toast.makeText(this, "Q: " + qq.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        } catch (IOException | JSONException e) {
//            Log.e("planets", "Error while reading questions from assets: " + e);
//        }
    }
}