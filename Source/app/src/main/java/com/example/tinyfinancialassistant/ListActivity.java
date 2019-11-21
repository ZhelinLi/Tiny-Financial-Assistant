package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ArrayList<DataObject> imageList;
    AllDBHelper db;
    private ListView listView;
    private ListAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.data_list);

        db = new AllDBHelper(this);
        imageList = db.getAllData();

        cAdapter = new ListAdapter(this, imageList);
        listView.setAdapter(cAdapter);
    }
}
