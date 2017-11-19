package cy.ac.uclancyprus.thesisapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maria_m on 10/29/2017.
 */

public class RegisterOpenHelper extends SQLiteOpenHelper {
    //Database Version
    public static final int DATABASE_VERSION = 1;

    //Database Name
    public static final String DATABASE_NAME = "register.db";

    //Database table name
    private static final String TABLE_USER = "users";

    //Database Columns
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_YEAR = "year";

    //SQL query
    private static final String CREATE_USER = "CREATE TABLE " + TABLE_USER + "(" +
            COLUMN_USERNAME + "TEXT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_SURNAME + " TEXT, " +
            COLUMN_YEAR + "INTEGER" + ")";

    //Drop the table using sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public RegisterOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // in our case, we simply delete all data and recreate the DB
        db.execSQL("DROP TABLE IF EXISTS Register;");
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_SURNAME, user.getSurname());
        values.put(COLUMN_YEAR, user.getYearfBirth());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public List<User> getAllUser() {
        String[] columns = {COLUMN_USERNAME, COLUMN_NAME, COLUMN_SURNAME, COLUMN_YEAR};
        String sortOrder = COLUMN_USERNAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                user.setSurname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)));
                user.setYearfBirth(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)));

                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }

//    //Update a user record
//    public void updateUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USERNAME, user.getUsername());
//        values.put(COLUMN_NAME, user.getName());
//        values.put(COLUMN_SURNAME, user.getSurname());
//        values.put(COLUMN_YEAR, user.getYearfBirth());
//
//        // updating row
//        db.update(TABLE_USER, values, COLUMN_USERNAME + " = ?",
//                new String[]{String.valueOf(user.getUsername())});
//        db.close();
//    }

//    //Delete a user record
//    public void deleteUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        // delete user record by id
//        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }

    //Check if user exist or not
    public boolean checkUser(String username) {

        String[] columns = {COLUMN_USERNAME};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USERNAME + " = ?";

        // selection argument
        String[] selectionArgs = {COLUMN_USERNAME};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }
}
