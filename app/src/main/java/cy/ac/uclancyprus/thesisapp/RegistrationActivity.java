package cy.ac.uclancyprus.thesisapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

        registerBtn = (Button) findViewById(R.id.register);
        nameTxt = (EditText) findViewById(R.id.name);
        surnameTxt = (EditText) findViewById(R.id.surname);
        usernameTxt = (EditText) findViewById(R.id.username);
        yearOfBirthTxt = (EditText) findViewById(R.id.yearOfBirth);

        nameAdd = nameTxt.getText().toString();
        surnameAdd = surnameTxt.getText().toString();
        usernameAdd = usernameTxt.getText().toString();
        yearOfBirthAdd = yearOfBirthTxt.getText().toString();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!nameAdd.equals("")) && (!surnameAdd.equals("")) && (!usernameAdd.equals("")) && (!yearOfBirthAdd.equals(""))) {
                    if (!usernameAdd.equals(RegisterOpenHelper.Register.USERNAME)) {
                        entries = entries + 1;
                        openDatabase(surnameAdd, nameAdd, surnameAdd, yearOfBirthAdd);
                        Toast.makeText(RegistrationActivity.this, "New Account: " + usernameAdd, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrationActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Open the User Database
    public void openDatabase(String uName, String fName, String sName, String bYear) {
        RegisterOpenHelper registerOpenHelper = new RegisterOpenHelper(RegistrationActivity.this);
        //make the database writable
        SQLiteDatabase sqLiteDatabase = registerOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RegisterOpenHelper.Register.USERNAME, uName);
        contentValues.put(RegisterOpenHelper.Register.FIRST_NAME, fName);
        contentValues.put(RegisterOpenHelper.Register.SURNAME, sName);
        contentValues.put(RegisterOpenHelper.Register.YEAR, bYear);

        //Insert the content values to database
        long row = sqLiteDatabase.insert(RegisterOpenHelper.Register.TABLE_NAME, null, contentValues);
        try {
            if (row != -1) {
                Toast.makeText(RegistrationActivity.this, "Registration is completed", Toast.LENGTH_SHORT).show();
                Toast.makeText(RegistrationActivity.this, uName + fName + uName + bYear, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(RegistrationActivity.this, "Registration is not completed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "EXCEPTION E", Toast.LENGTH_SHORT).show();
        }
        sqLiteDatabase.close();
    }

}
