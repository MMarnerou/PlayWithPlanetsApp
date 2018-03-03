package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Login extends AppCompatActivity {

    EditText usernameTxt;
    Button signInBtn, signAsGuestBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = findViewById(R.id.userNameTXT);
        signAsGuestBtn = findViewById(R.id.signAsGuest);
        signInBtn = findViewById(R.id.signInBtn);
        registerBtn = findViewById(R.id.register);

        // Register Button Intent to the RegisterActivity
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(Login.this, NewAccount.class);
                startActivity(registrationIntent);
            }
        });

        //Sign in as a guest Button (no need of account)
        signAsGuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuIntent = new Intent(Login.this, Modes.class);
                startActivity(menuIntent);
            }
        });

        //region Sign In button Intent to Menu Activity
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String userName = usernameTxt.getText().toString();
                    if (userName.equals("maria") && !userName.equals("")) {
                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent modesIntent = new Intent(Login.this, Modes.class);
                        modesIntent.putExtra("username", userName);
                        startActivity(modesIntent);
                    } else {
                        Toast.makeText(Login.this, "Username does not match", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Login.this, " Exception e", Toast.LENGTH_LONG).show();
                }
                //endregion
            }
        });

    }


    @Override
    protected void onResume() {
        //get sharedPreferences from NewAccount Activity
        SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
        String name = prefs.getString("name", "");
        String surname = prefs.getString("surname", "");
        String username = prefs.getString("username", "");
        Toast.makeText(this, "New Account is " + username, Toast.LENGTH_LONG).show();

        super.onResume();
    }


}
