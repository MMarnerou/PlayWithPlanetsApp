package cy.ac.uclancyprus.thesisapp;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadingModeActivity extends AppCompatActivity {

    TextView bookTXT_Reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_mode);
        ConstraintLayout clear = (ConstraintLayout)findViewById(R.id.constrain);
        ScrollView scroller = new ScrollView(ReadingModeActivity.this);
        bookTXT_Reading =(TextView) findViewById(R.id.bookTXT_Reading);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("PWC_users.txt")));
            String Line;
            while ((Line = reader.readLine()) != null) {
                bookTXT_Reading.append(Line);
                bookTXT_Reading.append("\n");
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"There is no text to show",Toast.LENGTH_LONG).show();
                }
            }
        }
        try {
            clear.removeView(bookTXT_Reading);
            scroller.addView(bookTXT_Reading);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
