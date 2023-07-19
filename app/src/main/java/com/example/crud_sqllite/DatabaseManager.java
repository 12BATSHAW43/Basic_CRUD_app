package com.example.crud_sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context ctx){
        context=ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper= new DatabaseHelper(context);
        sqLiteDatabase=dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }//end of close db

    public void insert(String student_name,String student_email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.STUDENT_NAME,student_name);
        contentValues.put(DatabaseHelper.STUDENT_EMAIL,student_email);
        sqLiteDatabase.insert(DatabaseHelper.DATABASE_TABLE,null,contentValues);
    }

    public Cursor fetchDataCursor()
    {
        String columns[]= new String[] {DatabaseHelper.STUDENT_ID,DatabaseHelper.STUDENT_NAME,DatabaseHelper.STUDENT_EMAIL};
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.DATABASE_TABLE,columns,null,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }//end of fetch

    public int update(long id, String name,String email)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.STUDENT_NAME,name);
        contentValues.put(DatabaseHelper.STUDENT_EMAIL,email);
        int num_of_rows_updated = sqLiteDatabase.update(DatabaseHelper.DATABASE_TABLE,contentValues,DatabaseHelper.STUDENT_ID+"="+id,null);
        return num_of_rows_updated;
    }

    public void delete(long id)
    {
        sqLiteDatabase.delete(DatabaseHelper.DATABASE_TABLE,DatabaseHelper.STUDENT_ID+"="+id,null);
    }//end of delete func

}//end of DatabaseManager class
