package cy.ac.uclancyprus.thesisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {


    TextView userId;
    ImageButton mode1Btn;
    ImageButton mode2Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent signIn_Intent = getIntent();
        String user = signIn_Intent.getStringExtra("user");

        //Start Welcome label
        Intent loginActivityIntent = getIntent();
        user = loginActivityIntent.getStringExtra("username");

        userId = (TextView) findViewById(R.id.userIDTXT_Menu);
        userId.setText("Welcome " + user);
        // End Welcome label


        mode1Btn = (ImageButton) findViewById(R.id.mode1BTN_Menu);
        mode1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mode1Intent = new Intent(MenuActivity.this, ReadingModeActivity.class);
                startActivity(mode1Intent);
            }
        });

        mode2Btn = (ImageButton) findViewById(R.id.mode2BTN_Menu);
        mode2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mode2Intent = new Intent(MenuActivity.this, QuizModeActivity.class);
                startActivity(mode2Intent);
            }
        });

    }
}
