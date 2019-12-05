package com.example.tinyfinancialassistant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListActivity extends AppCompatActivity {
    ArrayList<DataObject> dataList;
    AllDBHelper db;
    private ListView listView;
    private ListAdapter cAdapter;
    EditText searchText;
    Button searchButton, calendarButton, homeButton, typeButton, inputButton;
    Spinner typeHead;
    String lastDay, firstDay, today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.data_list);
        searchText = (EditText) findViewById(R.id.searchText);
        searchButton = (Button) findViewById(R.id.searchButton);
        calendarButton = (Button) findViewById(R.id.calendarButton);
        typeHead = (Spinner) findViewById(R.id.type_head);
        homeButton = findViewById(R.id.homeButton);
        inputButton = findViewById(R.id.inputButton);
        typeButton = findViewById(R.id.typeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
            }
        });

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, InputActivity.class));
            }
        });
        typeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, TypeActivity.class));
            }
        });


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        db = new AllDBHelper(this);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        today = "'" + df.format(c) + "'";
        if (lastDay == null || lastDay == "") lastDay = today;
        firstDay = "'1970-01-01'";

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.type_list));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeHead.setAdapter(typeAdapter);

        typeHead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("TYPE")) {
                    fillData(searchText, firstDay, lastDay, "");
                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fillData(searchText, firstDay, lastDay, "");

                        }
                    });
                }
                else {
                    fillData(searchText, firstDay, lastDay, typeHead.getSelectedItem().toString());
                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fillData(searchText, firstDay, lastDay, typeHead.getSelectedItem().toString());

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        dataList = db.getAllData("", firstDay, today, "");

        cAdapter = new ListAdapter(this, dataList);
        listView.setAdapter(cAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                simpleAlert(view, i);
                return false;
            }
        });


        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListActivity.this, CalendarActivity.class);
                startActivityForResult(i, 1);
                //startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
            }
        });
    }

    public void simpleAlert(View view, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setMessage("Delete this item?");
        builder.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),
                                "Deleted",
                                Toast.LENGTH_SHORT).show();
                        DataObject currentObj = dataList.get(i);
                        db = new AllDBHelper(getApplicationContext());
                        db.delete(currentObj.getId());
                        cAdapter.remove(cAdapter.getDataAt(i));
                        cAdapter.notifyDataSetChanged();

                    }
                });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.setCancelable(false);
        builder.show();
    }

    // This method is called when the calendar activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get String data from Intent
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String date =data.getStringExtra("Date");
                String temp = date.substring(1);
                date = temp.substring(0, date.length() - 2);
                String[] dateList = date.split(", ");
                firstDay = "'"+dateList[0]+"'";
                lastDay = "'"+dateList[dateList.length - 1]+"'";
                //lastDay = "'2019-12-04'";

                if (typeHead.getSelectedItem().toString().equals("TYPE")) {
                    fillData(searchText, firstDay, lastDay, "");
                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fillData(searchText, firstDay, lastDay, "");

                        }
                    });
                }

                else {
                    fillData(searchText, firstDay, lastDay, typeHead.getSelectedItem().toString());
                    searchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fillData(searchText, firstDay, lastDay, typeHead.getSelectedItem().toString());

                        }
                    });
                }

                Toast.makeText(ListActivity.this, firstDay + lastDay , Toast.LENGTH_LONG).show();
            }
        }
    }

    public void fillData(EditText searchText, String StartD, String EndD, String typeSelected) {
        String s = searchText.getText().toString();
        dataList = db.getAllData(s, StartD, EndD, typeSelected);
        cAdapter = new ListAdapter(getApplicationContext(), dataList);
        listView.setAdapter(cAdapter);
    }
}

