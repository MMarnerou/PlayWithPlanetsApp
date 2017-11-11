package cy.ac.uclancyprus.thesisapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by maria_m on 10/29/2017.
 */

public class RegisterOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "register.db";
    private static final String SQL_CREATE_REGISTER =
            "CREATE TABLE " + Register.TABLE_NAME + " (" +
                    Register.USERNAME + " TEXT NOT NULL, " +
                    Register.FIRST_NAME + " TEXT NOT NULL, " +
                    Register.SURNAME + " TEXT NOT NULL, " +
                    Register.YEAR + " TEXT NOT NULL);";

    public RegisterOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_REGISTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // in our case, we simply delete all data and recreate the DB
        db.execSQL("DROP TABLE IF EXISTS Register;");
        //onCreate(db);
    }

    public static abstract class Register implements BaseColumns {
        public static final String TABLE_NAME = "REGISTER";
        public static final String FIRST_NAME = "firstname";
        public static final String SURNAME = "surname";
        public static final String USERNAME = "username";
        public static final String YEAR = "year";
    }
}
