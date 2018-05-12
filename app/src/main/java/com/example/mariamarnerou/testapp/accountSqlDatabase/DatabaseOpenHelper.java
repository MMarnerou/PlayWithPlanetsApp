package com.example.mariamarnerou.testapp.accountSqlDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Maria Marnerou on 10-Mar-18.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "userAccounts.db";
    private static final String SQL_CREATE_USER_ACCOUNTS =
            "CREATE TABLE " + UserAccount.TABLE_NAME + " (" +
                    UserAccount.COLUMN_NAME +
                    UserAccount.COLUMN_AGE +
                    UserAccount.COLUMN_USERNAME +
                    UserAccount.COLUMN_GENDER +
                    ")";
    private static final String SQL_DELETE_USER_ACCOUNTS =
            "DROP TABLE IF EXISTS " + UserAccount.TABLE_NAME;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE accounts(" +
                " name TEXT," +
                " age TEXT," +
                " username TEXT," +
                " gender TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // in our case, we simply delete all data and recreate the DB
        db.execSQL("DROP TABLE IF EXISTS accounts;");
        //onCreate(db);
    }

    /* Inner class that defines the Accounts table contents */
    public static abstract class UserAccount implements BaseColumns {
        public static final String TABLE_NAME = "accounts";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_GENDER = "gender";
    }
}
