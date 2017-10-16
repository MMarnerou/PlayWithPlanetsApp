package cy.ac.uclancyprus.thesisapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username_TXT;
    Button register_BTN;
    Button signIn_BTN;
    String username_def = "maria";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_TXT = (EditText) findViewById(R.id.userNameTXT);
        register_BTN = (Button) findViewById(R.id.registerBTN);
        signIn_BTN = (Button) findViewById(R.id.signIn_BTN);

        // Register Button Intent to the RegisterActivity
        register_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register_Intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register_Intent.putExtra("username", username_def));
                Intent getRegister_Intent = getIntent();
                username_def = getRegister_Intent.getStringExtra("New Username");
            }
        });

        //region Sign In button Intent to Menu Activity
        signIn_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( username_def.equals(username_TXT.getText().toString()))  {
                    username_def = username_TXT.getText().toString();
                    Intent menu_Intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(menu_Intent.putExtra("user", username_def));
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong Username",Toast.LENGTH_LONG).show();
                }
            }
        });
        //endregion


    }
}
