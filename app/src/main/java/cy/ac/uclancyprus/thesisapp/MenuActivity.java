package cy.ac.uclancyprus.thesisapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {


    TextView userIDTXT_Menu;
    ImageButton mode1BTN_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent signIn_Intent = getIntent();
        String user = signIn_Intent.getStringExtra("user");
        userIDTXT_Menu = (TextView) findViewById(R.id.userIDTXT_Menu);
        userIDTXT_Menu.setText("Welcome " + user);

        mode1BTN_Menu = (ImageButton) findViewById(R.id.mode1BTN_Menu);
        mode1BTN_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Mode1_Intent = new Intent(MenuActivity.this, ReadingModeActivity.class);
                startActivity(Mode1_Intent);
            };
        });
    }
}
