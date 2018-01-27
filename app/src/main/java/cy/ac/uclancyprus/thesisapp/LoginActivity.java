package cy.ac.uclancyprus.thesisapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameTxt;
    Button registerBtn;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = (EditText) findViewById(R.id.userNameTXT);
        registerBtn = (Button) findViewById(R.id.registerBTN);
        signInBtn = (Button) findViewById(R.id.signInBtn);

        // Register Button Intent to the RegisterActivity
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuActivity_Intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(menuActivity_Intent);
            }
        });

        //region Sign In button Intent to Menu Activity
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String userName = usernameTxt.getText().toString();
                    if (userName.equals("maria") && !userName.equals("")) {
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent menuIntent = new Intent(LoginActivity.this, MenuActivity.class);
                        menuIntent.putExtra("username", userName);
                        startActivity(menuIntent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Username does not match", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, " Exception e", Toast.LENGTH_LONG).show();
                }
                //endregion
            }
        });
    }
}