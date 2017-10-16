package cy.ac.uclancyprus.thesisapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    ImageButton closeBtn;
    Button registerBtn;
    EditText usernameTxt;
    String usernameNew;
    Spinner yearOfBirthDdl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Start Close Button region
        closeBtn = (ImageButton) findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //End of Close Button region

        //Start Register Button region
        usernameTxt = (EditText) findViewById(R.id.usernameTxt);
        registerBtn = (Button) findViewById(R.id.registerBtn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn_Intent = getIntent();
                String username = signIn_Intent.getStringExtra("username");
                if (usernameTxt.getText().toString().equals(username)) {
                    Toast.makeText(RegisterActivity.this, "This username is being used", Toast.LENGTH_LONG).show();
                } else {
                    usernameNew = usernameTxt.getText().toString();
                    Toast.makeText(RegisterActivity.this, "Your account is ready!", Toast.LENGTH_SHORT).show();
                    Intent LoginInActivity_Intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    LoginInActivity_Intent.putExtra("New Username", usernameNew);
                    startActivity(LoginInActivity_Intent);
                }
            }
        });
        //End of Register Button Region

        //Start Year Of Birth DDL
        yearOfBirthDdl = (Spinner) findViewById(R.id.yearOfBirthDdl);

        ArrayList<String> years = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1930; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_item, years);
        yearOfBirthDdl.setAdapter(adapter);
    }
}
