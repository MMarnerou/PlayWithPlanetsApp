package com.example.mariamarnerou.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.mariamarnerou.testapp.model.Planet;

public class planetQuiz extends AppCompatActivity {

    TextView questionTxt, questionID;
    Button answer1Btn, answer2Btn, answer3Btn, answer4Btn;
    Question question;
    Integer answer;
    Planet planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_quiz);
//        answer1Btn = findViewById(R.id.answer1);
//        answer2Btn = findViewById(R.id.answer2);
//        answer3Btn = findViewById(R.id.answer3);
//        answer4Btn = findViewById(R.id.answer4);
//        questionTxt = findViewById(R.id.question);
//        questionID = findViewById(R.id.questionId);
//        Intent quizIntent = getIntent();
//        Integer planetChoice = quizIntent.getIntExtra("planet",1);
//        Toast.makeText(this, "planet: " + planetChoice, Toast.LENGTH_SHORT).show();

//        try {
//            final InputStream inputStream = getAssets().open("questions.json");
//            final Quiz quiz = new Gson().fromJson(new InputStreamReader(inputStream), Quiz.class);
//            Log.d("planets", quiz.toString());
//
//        } catch (IOException ioe) {
//            Log.e("planets", ioe.getMessage(), ioe);
//        }
//
//        try {
//            String json = convertStreamToString(getAssets().open("questions.json"));
//            JSONObject root = new JSONObject(json);
//            JSONObject planetObj = root.getJSONObject("planets");
//
//            Integer count = 0;
//            for (Iterator iterator = planetObj.keys(); iterator.hasNext(); ) {
//                Toast.makeText(this, "planet:" + count.toString() + planetObj.toString(), Toast.LENGTH_SHORT).show();
//                count = count + 1;
//                String planet = iterator.next().toString();
//
//                JSONObject planetJsonObject = planetObj.getJSONObject(planet);
//                System.out.println(planetJsonObject);
//                String welcome = planetJsonObject.getString("welcome");
//                System.out.println(welcome);
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
//                    Question qq = new Question(q, a, c);
//                    Toast.makeText(this, "Q: " + qq.toString(), Toast.LENGTH_SHORT).show();
//                    questionTxt.setText(qq.getQuestion());
//                    answer1Btn.setText(qq.getAnswer(0));
//                    answer2Btn.setText(qq.getAnswer(1));
//                    answer3Btn.setText(qq.getAnswer(2));
//                    answer4Btn.setText(qq.getAnswer(3));
//                    answer = qq.getCorrect();
//                }
//
//            }
//        } catch (IOException | JSONException e) {
//            Log.e("planets", "Error while reading questions from assets: " + e);
//            Toast.makeText(this, "Error while reading questions from assets", Toast.LENGTH_SHORT).show();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        questionTxt.setText(question.getQuestion());
//        answer1Btn.setText(question.getAnswer(0));
//        answer2Btn.setText(question.getAnswer(1));
//        answer3Btn.setText(question.getAnswer(2));
//        answer4Btn.setText(question.getAnswer(3));
//        answer = question.getCorrect();
//        question.shuffleAnswers();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        planet = (Planet) getIntent().getSerializableExtra("planet");
//        Log.d("planets", "PPPP: " + planet);
//
//        Toast.makeText(this, "pp: " + planet.getName(), Toast.LENGTH_SHORT).show();
        questionTxt.setText(planet.getQuestion(0).getQuestion());
        answer1Btn.setText(planet.getQuestion(1).getAnswer(0));
        answer2Btn.setText(planet.getQuestion(0).getAnswer(1));
        answer3Btn.setText(planet.getQuestion(0).getAnswer(0));
        answer4Btn.setText(planet.getQuestion(0).getAnswer(1));
    }
}
