package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {
    SQLiteDatabase mDB;
    EditText inputEditText, inputNote;
    Button inputFoodButton, inputTransportationButton, inputStudyButton, inputHousingButton,
            inputEntertainmentButton, inputShoppingButton, inputCleaningButton,
            inputPersonalCareButton, inputHobbyButton, inputOtherButton, inputAddItemButton;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        AllDBHelper dbHelper = new AllDBHelper(this);
        mDB = dbHelper.getWritableDatabase();

        inputEditText = findViewById(R.id.inputEditText);
        inputNote = findViewById(R.id.inputNote);
        inputFoodButton = findViewById(R.id.inputFoodButton);
        inputTransportationButton = findViewById(R.id.inputTransportationButton);
        inputStudyButton = findViewById(R.id.inputStudyButton);
        inputHousingButton = findViewById(R.id.inputHousingButton);
        inputEntertainmentButton = findViewById(R.id.inputEntertainmentButton);
        inputShoppingButton = findViewById(R.id.inputShoppingButton);
        inputCleaningButton = findViewById(R.id.inputCleaningButton);
        inputPersonalCareButton = findViewById(R.id.inputPersonalCareButton);
        inputHobbyButton = findViewById(R.id.inputHobbyButton);
        inputOtherButton = findViewById(R.id.inputOtherButton);
        inputAddItemButton = findViewById(R.id.inputAddItemButton);

        inputFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeFood();
            }
        });
        inputTransportationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeTransportation();
            }
        });
        inputStudyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeStudy();
            }
        });
        inputHousingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeHousing();
            }
        });
        inputEntertainmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeEntertainment();
            }
        });
        inputShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeShopping();
            }
        });
        inputCleaningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeCleaning();
            }
        });
        inputPersonalCareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypePersonalCare();
            }
        });
        inputHobbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeHobby();
            }
        });
        inputOtherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeOther();
            }
        });
        inputAddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }
    private void setTypeFood() {type = "Food";}
    private void setTypeTransportation() {type = "Transportation";}
    private void setTypeStudy() {type = "Study";}
    private void setTypeHousing() {type = "Housing";}
    private void setTypeEntertainment() {type = "Entertainment";}
    private void setTypeShopping() {type = "Shopping";}
    private void setTypeCleaning() {type = "Cleaning";}
    private void setTypePersonalCare() {type = "PersonalCare";}
    private void setTypeHobby() {type = "Hobby";}
    private void setTypeOther() {type = "Other";}
    private void addItem() {
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