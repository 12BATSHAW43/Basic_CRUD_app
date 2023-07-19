package com.example.crud_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edt_id,edt_name,edt_email;
    Button insert_btn,fetch_btn,delete_btn,update_btn;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_id=findViewById(R.id.edt_student_id);
        edt_name=findViewById(R.id.edt_student_name);
        edt_email=findViewById(R.id.edt_student_email);
        insert_btn=findViewById(R.id.btn_insert);
        fetch_btn=findViewById(R.id.btn_fetch);
        delete_btn=findViewById(R.id.btn_DELETE);
        update_btn=findViewById(R.id.btn_update);

        databaseManager=new DatabaseManager(this);
        try {
            databaseManager.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager.insert(edt_name.getText().toString(),edt_email.getText().toString());
            }
        });//end of insert

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager.update(Long.parseLong(edt_id.getText().toString()),edt_name.getText().toString(),edt_email.getText().toString());
            }
        });//end of update

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager.delete(Long.parseLong(edt_id.getText().toString()));
            }
        });//end of delete

        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = databaseManager.fetchDataCursor();
                do{
                    String ID =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.STUDENT_ID));
                    String NAME =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.STUDENT_NAME));
                    String EMAIl =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.STUDENT_EMAIL));
                    Log.i("DATABASE_TAG","i have read ID : " + ID + " NAME: "+NAME +" EMAIL :" +EMAIl);
                }
                while (cursor.moveToNext());//end of do while
            }
        });
    }//end of OnCreate


}//end of mainActivity class