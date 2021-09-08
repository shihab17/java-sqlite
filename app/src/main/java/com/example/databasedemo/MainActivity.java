package com.example.databasedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radioGroup;
    RadioButton genderRB;
    TextView txtName, txtAge;
    Button btnSubmit, btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        txtName = findViewById(R.id.name);
        txtAge = findViewById(R.id.age);
        radioGroup = findViewById(R.id.gender);
        btnSubmit = findViewById(R.id.submit);
        btnDisplay = findViewById(R.id.displayData);
        btnSubmit.setOnClickListener(this);
        btnDisplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.submit) {
            int selectedGender = radioGroup.getCheckedRadioButtonId();
            genderRB = findViewById(selectedGender);
            String name = txtName.getText().toString();
            String age = txtAge.getText().toString();
            String gender = genderRB.getText().toString();
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
            long rowID = myDatabaseHelper.insertData(name, age, gender);
            if (rowID == -1) {
                Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Successfully Insert", Toast.LENGTH_SHORT).show();
            }

        }
        if (view.getId() == R.id.displayData) {
            Toast.makeText(this, "Display Data Clicked", Toast.LENGTH_SHORT).show();
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
            Cursor cursor = myDatabaseHelper.displayAllData();

            if (cursor.getCount() == 0) {
                showData("Error", "No data Found ");
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    stringBuffer.append("ID " + cursor.getString(0));
                    stringBuffer.append("\n Name " + cursor.getString(1));
                    stringBuffer.append("\n Age " + cursor.getString(2));
                    stringBuffer.append("\n Gender " + cursor.getString(3));

                }
                showData("Display Information", stringBuffer.toString());


            }
        }
    }


    public void showData(String title, String data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(true);
        builder.setMessage(data);
        builder.show();
    }
}