package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {
    SQLiteDatabase mDB;
    EditText inputEditText, inputNote;
    Button inputFoodButton, inputTransportationButton, inputStudyButton, inputHousingButton,
            inputEntertainmentButton, inputClothingButton, inputCleaningButton,
            inputPersonalCareButton, inputHobbyButton, inputOtherButton, inputAddItemButton,
            homeButton, typeButton, listButton;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        homeButton = findViewById(R.id.homeButton);
        typeButton = findViewById(R.id.typeButton);
        listButton = findViewById(R.id.listButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InputActivity.this, MainActivity.class));
            }
        });

        typeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InputActivity.this, TypeActivity.class));
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InputActivity.this, ListActivity.class));
            }
        });

        AllDBHelper dbHelper = new AllDBHelper(this);
        mDB = dbHelper.getWritableDatabase();
        inputEditText = findViewById(R.id.inputEditText);
        inputNote = findViewById(R.id.inputNote);
        inputFoodButton = findViewById(R.id.inputFoodButton);
        inputTransportationButton = findViewById(R.id.inputTransportationButton);
        inputStudyButton = findViewById(R.id.inputStudyButton);
        inputHousingButton = findViewById(R.id.inputHousingButton);
        inputEntertainmentButton = findViewById(R.id.inputEntertainmentButton);
        inputClothingButton = findViewById(R.id.inputClothingButton);
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
        inputClothingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTypeClothing();
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
    private void setTypeFood() {
        type = "Food";
        Toast.makeText(getApplicationContext(), "Food", Toast.LENGTH_SHORT).show();
    }
    private void setTypeTransportation() {
        type = "Transportation";
        Toast.makeText(getApplicationContext(), "Transportation", Toast.LENGTH_SHORT).show();
    }
    private void setTypeStudy() {
        type = "Study";
        Toast.makeText(getApplicationContext(), "Study", Toast.LENGTH_SHORT).show();
    }
    private void setTypeHousing() {
        type = "Housing";
        Toast.makeText(getApplicationContext(), "Housing", Toast.LENGTH_SHORT).show();
    }
    private void setTypeEntertainment() {
        type = "Entertainment";
        Toast.makeText(getApplicationContext(), "Entertainment", Toast.LENGTH_SHORT).show();
    }
    private void setTypeClothing() {
        type = "Clothing";
        Toast.makeText(getApplicationContext(), "Clothing", Toast.LENGTH_SHORT).show();
    }
    private void setTypeCleaning() {
        type = "Cleaning";
        Toast.makeText(getApplicationContext(), "Cleaning", Toast.LENGTH_SHORT).show();
    }
    private void setTypePersonalCare() {
        type = "PersonalCare";
        Toast.makeText(getApplicationContext(), "Personal Care", Toast.LENGTH_SHORT).show();
    }
    private void setTypeHobby() {
        type = "Hobby";
        Toast.makeText(getApplicationContext(), "Hobby", Toast.LENGTH_SHORT).show();
    }
    private void setTypeOther() {
        type = "Other";
        Toast.makeText(getApplicationContext(), "Other", Toast.LENGTH_SHORT).show();
    }
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
            inputNote.getText().clear();
        }
    }

}
