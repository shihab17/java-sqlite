package com.example.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Student.db";
    private static final String ID = "_id";
    private static final String NAME = "_name";
    private static final String AGE = "_age";
    private static final String GENDER = "_gender";
    private static final String TABLE_NAME = "students_details";
    private static final int VERSION_NUMBER = 1;
    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context,"OnCreate is called: ",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR (255), " + AGE + " INTEGER, "+GENDER+" VARCHAR(255)) ");
        } catch (Exception e) {
            Toast.makeText(context,"Exception: "+ e,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            Toast.makeText(context,"onUpgrade is called: ",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+"");
            onCreate(sqLiteDatabase);
        }
        catch (Exception e){
            Toast.makeText(context,"Exception: "+ e,Toast.LENGTH_SHORT).show();
        }

    }
}
