package com.example.mariamarnerou.testapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mariamarnerou.testapp.accountSqlDatabase.DatabaseOpenHelper;

public class Login extends AppCompatActivity {

    EditText usernameTxt;
    Button signInBtn, signAsGuestBtn, newAccountBtn;
    String name, username;
    Boolean isResponseEmpty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = findViewById(R.id.userNameTXT);
        signAsGuestBtn = findViewById(R.id.signAsGuest);
        signInBtn = findViewById(R.id.signInBtn);
        newAccountBtn = findViewById(R.id.newAccount);

        // Register Button Intent to the RegisterActivity
        newAccountBtn.setOnClickListener(new View.OnClickListener() {
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
                String userName = usernameTxt.getText().toString();

                if (userName != null) {
                    //Get account from database
                    String get = "SELECT * FROM accounts";
                    DbAccount(get);
                    if (!isResponseEmpty) {
                        if (userName.equals(username)) {
                            Toast.makeText(Login.this, "Η σύνδεση ως " + username + " είναι επιτυχής", Toast.LENGTH_SHORT).show();
                            Intent modesIntent = new Intent(Login.this, Modes.class);
                            modesIntent.putExtra("username", username);
                            startActivity(modesIntent);
                        } else {
                            Toast.makeText(Login.this, "Το Συνθηματικό σας δεν είναι αποδεκτό.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "Δεν υπάρχει αυτό το Συνθηματικό", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Το Συνθηματικό είναι υποχρεωτικό", Toast.LENGTH_LONG).show();
                }//endregion
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void DbAccount(String sql) {
        //OPEN THE DATABASE AND REQUEST FOR AN SPECIFIC username
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(Login.this);
        SQLiteDatabase db = databaseOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
            username = cursor.getString(cursor.getColumnIndex("username"));
        }
        isResponseEmpty = true;
        if (cursor.getCount() <= 0) {
            cursor.close();
            isResponseEmpty = true;
        } else {
            isResponseEmpty = false;
        }
        cursor.close();
        databaseOpenHelper.close();
    }
}
