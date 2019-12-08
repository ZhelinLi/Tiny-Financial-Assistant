package com.example.tinyfinancialassistant;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<DataObject> {

    private ArrayList<DataObject> dataList = new ArrayList<>();
    private Context mContext;
    TextView noteView, timeView, costView;
    ImageView typeView;
    AllDBHelper db;
    Bitmap bm;

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
        switch(currentData.getType()) {
            case "Food":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.food);
                break;
            case "Transportation":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.transportation);
                break;
            case "Study":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.study);
                break;
            case "Housing":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.housing);
                break;
            case "Entertainment":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.entertainment);
                break;
            case "Clothing":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.clothing);
                break;
            case "Cleaning":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cleaning);
                break;
            case "PersonalCare":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.care);
                break;
            case "Hobby":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.hobby);
                break;
            case "Other":
                bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.other);
                break;
        }
        typeView = (ImageView) listItem.findViewById(R.id.typeView);
        noteView = (TextView) listItem.findViewById(R.id.noteView);
        timeView = (TextView) listItem.findViewById(R.id.timeView);
        costView = (TextView) listItem.findViewById(R.id.costView);

        typeView.setImageBitmap(bm);
        noteView.setText(currentData.getNote());
        timeView.setText((currentData.getTime()).toString());
        costView.setText(String.valueOf(currentData.getCost()));
        return listItem;
    }

    public DataObject getDataAt(int position) {
        return dataList.get(position);
    }
}