package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<DataObject> dataList;
    AllDBHelper db;
    private ListView listView;
    private ListAdapter cAdapter;
    EditText searchNoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.data_list);

        db = new AllDBHelper(this);
        dataList = db.getAllData();

        cAdapter = new ListAdapter(this, dataList);
        listView.setAdapter(cAdapter);
    }

}
