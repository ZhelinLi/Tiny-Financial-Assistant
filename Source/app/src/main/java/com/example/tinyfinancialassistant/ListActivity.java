package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                simpleAlert(view, i);
                return false;
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
                                "deleted",
                                Toast.LENGTH_SHORT).show();
                        cAdapter.remove(cAdapter.getDataAt(i));
                        cAdapter.notifyDataSetChanged();
                        db = new AllDBHelper(getApplicationContext());
                        // cannot select current view in the list????
                        /*
                         DataObject currentObj = dataList.get(i);
                         Log.d("********************",currentObj.getNote());
                         
                         */
                        // db.delete(currentObj.getId());
                    }
                });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.setCancelable(false);
        builder.show();
    }
}
