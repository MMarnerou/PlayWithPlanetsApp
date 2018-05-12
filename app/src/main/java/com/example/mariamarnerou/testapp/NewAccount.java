package com.example.mariamarnerou.testapp;

/**
 * Created by Maria Marnerou on 10-Mar-18.
 */

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mariamarnerou.testapp.accountSqlDatabase.DatabaseOpenHelper;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class NewAccount extends AppCompatActivity {
    EditText usernameTxt, nameTxt;
    RadioButton radioMale, radioFemale;
    Button newAccountBtn;
    String gender, username, name, age;
    int numOfRowsExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        usernameTxt = findViewById(R.id.username);
        nameTxt = findViewById(R.id.name);
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
                name = nameTxt.getText().toString();
                age = "25";

                //get the spinner from the xml.
                Spinner dropdown = findViewById(R.id.ageSpinner);
                //create a list of items for the spinner.
                String[] items = new String[]{"8-10 χρονών", "11-14 χρονών", "15-18 χρονών", "18+ χρονών"};
                //There are multiple variations of this, but this is the basic variant.
                ArrayAdapter<String> adapter = new ArrayAdapter<>(NewAccount.this, android.R.layout.simple_dropdown_item_1line, items);
                //set the spinners adapter to the previously created one.
                dropdown.setAdapter(adapter);

                age = dropdown.getSelectedItem().toString();

                if ((!(usernameTxt.getText().toString().isEmpty())) && (!(nameTxt.getText().toString().isEmpty()))) {
                    DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(NewAccount.this);
                    SQLiteDatabase database = databaseOpenHelper.getReadableDatabase();
                    String sqlQuery = "SELECT * FROM accounts WHERE username=" + " '" + username + "' ";
                    database.execSQL(sqlQuery);
                    Cursor cursor = database.rawQuery(sqlQuery, null);
                    numOfRowsExist = cursor.getCount();
                    cursor.close();
                    if (numOfRowsExist == 0) {
                        //Save the data in sharedPreferences
                        SharedPreferences accountprefs = getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = accountprefs.edit();
                        editor.putString("name", nameTxt.getText().toString());
                        editor.commit();
                        insert(username, age);
                    } else {
                        Toast.makeText(NewAccount.this, "Αυτό το συνθηματικό έχει χρησιμοποιηθεί ήδη.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NewAccount.this, "Όλα τα πεδία πρέπει να είναι συμπληρωμένα.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insert(String usrnm, String ageStr) {
        Toast.makeText(NewAccount.this, "Δημιουργία Λογαριασμού", Toast.LENGTH_SHORT).show();

        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(NewAccount.this);
        SQLiteDatabase database = databaseOpenHelper.getWritableDatabase();
//        database.execSQL("DROP TABLE IF EXISTS accounts;");
//        database.execSQL("CREATE TABLE accounts(" +
//                " name TEXT," +
//                " age TEXT," +
//                " username TEXT," +
//                " gender TEXT);");
        database.execSQL("INSERT INTO accounts VALUES ('" + name + "', '" + ageStr + "', '" + usrnm + "', '" + gender + "')");
        Cursor cursor = database.rawQuery("SELECT * FROM accounts", new String[0]);
        int numOfRows = cursor.getCount();
        Toast.makeText(this, "added in DB - now: " + numOfRows, Toast.LENGTH_SHORT).show();
        cursor.close();
        finish(); // exit activity
    }
}
