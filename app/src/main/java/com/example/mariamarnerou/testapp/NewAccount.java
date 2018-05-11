package com.example.mariamarnerou.testapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mariamarnerou.testapp.accountSqlDatabase.DatabaseOpenHelper;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class NewAccount extends AppCompatActivity {
    EditText usernameTxt, nameTxt, surnameTxt;
    RadioButton radioMale, radioFemale;
    Button newAccountBtn;
    String gender, username, name, surname;
    int numOfRowsExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        usernameTxt = findViewById(R.id.username);
        nameTxt = findViewById(R.id.name);
        surnameTxt = findViewById(R.id.surname);
        radioFemale = findViewById(R.id.radioF);
        radioMale = findViewById(R.id.radioM);
        newAccountBtn = findViewById(R.id.newAccount);

        newAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioFemale.isChecked()) {
                    gender = "Female";
                } else {
                    gender = "Male";
                }
                username = usernameTxt.getText().toString();
                surname = surnameTxt.getText().toString();
                name = nameTxt.getText().toString();

                if ((!(usernameTxt.getText().toString().isEmpty())) && (!(nameTxt.getText().toString().isEmpty())) && (!(surnameTxt.getText().toString().isEmpty()))) {
                    DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(NewAccount.this);
                    SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
                    String sqlQuery = "SELECT * FROM accounts WHERE username=" + " '" + username + "' ";
                    //database.execSQL(sqlQuery);
                    Cursor cursor = database.rawQuery(sqlQuery, null);
                    numOfRowsExist = cursor.getCount();
                    cursor.close();
                    if (numOfRowsExist == 0) {
                        //Save the data in sharedPreferences
                        SharedPreferences accountprefs = getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = accountprefs.edit();
                        editor.putString("name", nameTxt.getText().toString());
                        editor.putString("surname", surnameTxt.getText().toString());
                        editor.commit();
                        insert(username);
                    } else {
                        Toast.makeText(NewAccount.this, "Αυτό το συνθηματικό έχει χρησιμοποιηθεί ήδη.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NewAccount.this, "Όλα τα πεδία πρέπει να είναι συμπληρωμένα.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insert(String usrnm) {
        // Toast.makeText(NewAccount.this, "Δημιουργία Λογαριασμού", Toast.LENGTH_SHORT).show();

        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(NewAccount.this);
        SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
        database.execSQL("INSERT INTO accounts (name, surname, username, gender) VALUES ('" + name + "', '" + surname + "', '" + usrnm + "', '" + gender + "')");
        Cursor cursor = database.rawQuery("SELECT * FROM accounts", new String[0]);
        int numOfRows = cursor.getCount();
        //Toast.makeText(this, "added in DB - now: " + numOfRows, Toast.LENGTH_SHORT).show();
        cursor.close();
        finish(); // exit activity
    }

    private void verifyUsername(String usrnm) {//return 1 or 0 if the username exist or not in database
    }
}
