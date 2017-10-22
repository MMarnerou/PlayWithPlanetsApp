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
    String usernameDef = "maria";

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
                Intent register_Intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register_Intent.putExtra("username", usernameDef));

            }
        });

        //region Sign In button Intent to Menu Activity
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent getRegister_Intent = getIntent();
                    String newUsername = "";
                    newUsername = getRegister_Intent.getStringExtra("New Username");
                    if (newUsername != null && !newUsername.equals("")) {
                        usernameDef = newUsername;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                usernameDef = getRegister_Intent.getStringExtra("New Username");
                if (!usernameTxt.getText().toString().equals("")) {
                    if (usernameTxt.getText().toString().equals(usernameDef)) {
                        usernameDef = usernameTxt.getText().toString();
                        Intent menu_Intent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(menu_Intent.putExtra("user", usernameDef));
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong Username", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Enter a username.", Toast.LENGTH_LONG).show();
                }
            }
        });
        //endregion

        //Google Sign In Button


        //End of Google Sign In Button
    }
}
