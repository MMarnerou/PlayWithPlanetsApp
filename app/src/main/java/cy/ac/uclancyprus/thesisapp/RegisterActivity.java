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
    ImageButton closeBTN_Reg;
    Button registerBTN_Reg;
    EditText usernameTXT_Reg;
    String usernameNew;
    Spinner yearOfBirth_DDL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Start Close Button region
        closeBTN_Reg = (ImageButton) findViewById(R.id.closeBTN_Register);
        closeBTN_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //End of Close Button region

        //Start Register Button region
        usernameTXT_Reg = (EditText) findViewById(R.id.usernameTXT_Register);
        registerBTN_Reg = (Button) findViewById(R.id.registerBTN_Register);
        registerBTN_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn_Intent = getIntent();
                String username = signIn_Intent.getStringExtra("username");
                if (usernameTXT_Reg.getText().toString().equals(username)) {
                    Toast.makeText(RegisterActivity.this, "This username is being used", Toast.LENGTH_LONG).show();
                } else {
                    usernameNew = usernameTXT_Reg.getText().toString();
                    Toast.makeText(RegisterActivity.this, "Your account is ready!", Toast.LENGTH_SHORT).show();
                    Intent LoginInActivity_Intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    LoginInActivity_Intent.putExtra("New Username", usernameNew);
                    startActivity(LoginInActivity_Intent);
                }
            }
        });
        //End of Register Button Region

        //Start Year Of Birth DDL
        yearOfBirth_DDL = (Spinner) findViewById(R.id.year_DDL);

        ArrayList<String> years = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1930; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_item, years);
        yearOfBirth_DDL.setAdapter(adapter);
    }
}
