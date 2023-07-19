package com.example.crud_sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //we are removing other parameters in constructor and manually adding them here
    static final String DATABASE_NAME ="MYDATABASE";
    static final int DATABASE_VERSION =1;
    static final String DATABASE_TABLE ="STUDENT";
    static final String  STUDENT_ID = "id"  ;
    static final String  STUDENT_NAME = "name"  ;
    static final String  STUDENT_EMAIL = "email" ;

    private static final String CREATE_QUERY="CREATE TABLE "+DATABASE_TABLE+" ("+STUDENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+STUDENT_NAME+" TEXT, "+STUDENT_EMAIL+" TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
    }
}
