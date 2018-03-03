package com.example.mariamarnerou.testapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class NewAccount extends AppCompatActivity {
    EditText usernameTxt, nameTxt, surnameTxt;
    RadioButton radioMale, radioFemale;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        usernameTxt = findViewById(R.id.userNameTXT);
        nameTxt = findViewById(R.id.name);
        surnameTxt = findViewById(R.id.surname);
        radioFemale = findViewById(R.id.radioF);
        radioMale = findViewById(R.id.radioM);
        registerBtn = findViewById(R.id.register);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save the data in sharedPreferences
                SharedPreferences accountprefs = getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = accountprefs.edit();
                editor.putString("name", nameTxt.getText().toString());
                editor.putString("surname", surnameTxt.getText().toString());
                editor.commit();
                finish(); // exit activity
            }
        });
    }
}
