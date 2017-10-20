package cy.ac.uclancyprus.thesisapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMarnerou on 10/17/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //Database Name
    public static String DATABASE_QUESTION = "questionsDb";
    //Current version of database
    private static final int DATABASE_VERSION = 1;
    //Database table name
    private static final String TABLE_QUESTIONS = "QuestionBank";
    //All fields used in a database
    private static final String QUESTION_ID = "id";
    private static final String QUESTION = "question";
    private static final String CHOICE_1 = "choice1";
    private static final String CHOICE_2 = "choice2";
    private static final String CHOICE_3 = "choice3";
    private static final String CHOICE_4 = "choice4";
    private static final String ANSWER = "answer";

    //Create query
    private static final String CREATE_QUESTION_TABLE = "CREATE TABLE "
            + TABLE_QUESTIONS + "(" + QUESTION_ID + " TEXT,"
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT,"
            + CHOICE_1 + " TEXT, " + CHOICE_2 + " TEXT, "
            + CHOICE_3 + " TEXT, " + CHOICE_4 + " TEXT, "
            + ANSWER + " TEXT);";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //https://www.youtube.com/watch?v=81FLyzhROiQ
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_QUESTIONS);//create the Question_Table
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + CREATE_QUESTION_TABLE);
        onCreate(sqLiteDatabase);
    }

    //Add question detail in question Table

    public long addInitialQuestion (Questions questions){
        SQLiteDatabase db = this.getWritableDatabase();
        //Creating content values
        ContentValues values = new ContentValues();
        values.put(QUESTION, questions.getQuestion());
        values.put(CHOICE_1, questions.getChoice(0));
        values.put(CHOICE_2, questions.getChoice(1));
        values.put(CHOICE_3, questions.getChoice(2));
        values.put(CHOICE_4, questions.getChoice(3));
        values.put(ANSWER, questions.getAnswer());
        //insert a row in question table
        long insert = db.insert(TABLE_QUESTIONS, null, values);
        return insert;
    }

    //To Extract data from database and save it in an ArrayList of data type Question

    public List<Questions> getQuestionsList() {
        List<Questions> questionsArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all records and adding to the list
        if (cursor.moveToFirst()) {
            do {
                Questions question = new Questions();

                String questText = cursor.getString(cursor.getColumnIndex(QUESTION));
                question.setQuestion(questText);

                String choice1Text = cursor.getString(cursor.getColumnIndex(CHOICE_1));
                question.setChoices(0, choice1Text);

                String choice2Text = cursor.getString(cursor.getColumnIndex(CHOICE_2));
                question.setChoices(1, choice2Text);

                String choice3Text = cursor.getString(cursor.getColumnIndex(CHOICE_3));
                question.setChoices(2, choice3Text);

                String choice4Text = cursor.getString(cursor.getColumnIndex(CHOICE_4));
                question.setChoices(3, choice4Text);

                String answerText = cursor.getString(cursor.getColumnIndex(ANSWER));
                question.setQuestion(answerText);

                //add to Questions List
                questionsArrayList
            }
        }

    }
        return questionsArrayList;
}
