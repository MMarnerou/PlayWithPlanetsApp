package cy.ac.uclancyprus.thesisapp;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class ReadingModeActivity extends AppCompatActivity {

    TextView bookTXT_Reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_mode);

        ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.constrain);

       ScrollView scroller = new ScrollView(ReadingModeActivity.this);
        bookTXT_Reading =(TextView) findViewById(R.id.bookTXT_Reading);
        try {
            cl.removeView(bookTXT_Reading);
            scroller.addView(bookTXT_Reading);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
