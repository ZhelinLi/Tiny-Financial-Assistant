package com.example.tinyfinancialassistant;


import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListAdapter extends ArrayAdapter<DataObject> {

    private ArrayList<DataObject> dataList = new ArrayList<>();
    private Context mContext;
    TextView typeView, noteView, timeView, costView;
    AllDBHelper db;

    public ListAdapter(Context context, ArrayList<DataObject> list) {
        super(context, 0, list);
        dataList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_adapter,parent,false);

        final DataObject currentData = dataList.get(position);

        typeView = (TextView) listItem.findViewById(R.id.typeView);
        noteView = (TextView) listItem.findViewById(R.id.noteView);
        timeView = (TextView) listItem.findViewById(R.id.timeView);
        costView = (TextView) listItem.findViewById(R.id.costView);

        typeView.setText(currentData.getType());
        noteView.setText(currentData.getNote());
        Timestamp stamp = currentData.getTime();
        Date date = new Date(stamp.getTime());
        timeView.setText(date.toString());
        costView.setText(String.valueOf(currentData.getCost()));

        return listItem;
    }

}