package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {
    SQLiteDatabase mDB;
    EditText inputEditText, inputNote;
    RadioButton inputFoodButton, inputTransportationButton, inputStudyButton, inputHousingButton,
            inputEntertainmentButton, inputShoppingButton, inputCleaningButton,
            inputPersonalCareButton, inputHobbyButton, inputOtherButton, inputAddItemButton;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        AllDBHelper dbHelper = new AllDBHelper(this);
        mDB = dbHelper.getWritableDatabase();

    }
    public void setTypeFood(View view) {type = "Food";}
    public void setTypeTransportation(View view) {type = "Transportation";}
    public void setTypeStudy(View view) {type = "Study";}
    public void setTypeHousing(View view) {type = "Housing";}
    public void setTypeEntertainment(View view) {type = "Entertainment";}
    public void setTypeShopping(View view) {type = "Shopping";}
    public void setTypeCleaning(View view) {type = "Cleaning";}
    public void setTypePersonalCare(View view) {type = "PersonalCare";}
    public void setTypeHobby(View view) {type = "Hobby";}
    public void setTypeOther(View view) {type = "Other";}
    public void addItem(View view) {
        if (inputEditText.getText().toString().trim().length() == 0 || type == "" || type == null) {
            Toast.makeText(this, "Please input value or select type", Toast.LENGTH_SHORT).show();
        }
        else {
            String text = inputNote.getText().toString();
            float amount = Float.valueOf(inputEditText.getText().toString());
            ContentValues cv = new ContentValues();
            cv.put(AllContract.AllEntry.COLUMN_TYPE, type);
            cv.put(AllContract.AllEntry.COLUMN_PRICE, amount);
            cv.put(AllContract.AllEntry.COLUMN_NOTE, text);

            mDB.insert(AllContract.AllEntry.TABLE_NAME, null, cv);
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
            inputEditText.getText().clear();
        }
    }

}
