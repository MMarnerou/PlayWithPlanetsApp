package cy.ac.uclancyprus.thesisapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizActivity extends AppCompatActivity {

    TextView questionTxt;
    String question;
    ImageButton quiz1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quiz1 = (ImageButton) findViewById(R.id.quiz1);
        quiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(getAssets().open("Quiz1.txt")));
                    String Line;
                    while ((Line = reader.readLine()) != null) {

                        questionTxt.append(Line);
                        // questionTxt.append("\n");
                    }
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "There is no text to show", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }
}
