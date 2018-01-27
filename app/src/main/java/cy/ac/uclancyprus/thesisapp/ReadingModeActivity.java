package cy.ac.uclancyprus.thesisapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class ReadingModeActivity extends AppCompatActivity {

    TextView bookTXT_Reading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_mode);

        //Start Slide Page
        bookTXT_Reading =(TextView) findViewById(R.id.bookTXT_Reading);
        try {
            InputStream book = getAssets().open("Quiz1.txt");
            int size = book.available();

            //Read the entire asset into a local byte buffer
            byte[] buffer = new byte[size];
            book.read(buffer);
            book.close();

            String text = new String(buffer);
            bookTXT_Reading.setText(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //End Slide Page
    }
}
