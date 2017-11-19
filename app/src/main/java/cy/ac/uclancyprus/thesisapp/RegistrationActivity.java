package cy.ac.uclancyprus.thesisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    Button registerBtn;
    EditText nameTxt, surnameTxt, yearOfBirthTxt, usernameTxt;
    String nameAdd, surnameAdd, usernameAdd;
    String yearOfBirthAdd, entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final RegisterOpenHelper registerOpenHelper = new RegisterOpenHelper(this);
//        registerOpenHelper.open();

        registerBtn = (Button) findViewById(R.id.register);
        nameTxt = (EditText) findViewById(R.id.name);
        surnameTxt = (EditText) findViewById(R.id.surname);
        usernameTxt = (EditText) findViewById(R.id.username);
        yearOfBirthTxt = (EditText) findViewById(R.id.yearOfBirth);

        //Start Registration Button
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameAdd = nameTxt.getText().toString();
                surnameAdd = surnameTxt.getText().toString();
                usernameAdd = usernameTxt.getText().toString();
                yearOfBirthAdd = yearOfBirthTxt.getText().toString();

                if (usernameAdd.isEmpty() && nameAdd.isEmpty() && surnameAdd.isEmpty() && yearOfBirthAdd.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (!registerOpenHelper.checkUser(usernameAdd)) {
                        User user = new User();
                        registerOpenHelper.addUser(user);
                        Toast.makeText(RegistrationActivity.this, "New Account: " + usernameAdd, Toast.LENGTH_SHORT).show();
                        Intent loginActivityIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(loginActivityIntent);
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Username is taken!" + usernameAdd, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
