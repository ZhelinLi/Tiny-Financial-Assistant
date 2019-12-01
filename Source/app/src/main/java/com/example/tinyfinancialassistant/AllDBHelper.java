package com.example.tinyfinancialassistant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tinyfinancialassistant.AllContract.*;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.util.ArrayList;

public class AllDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "allList.db";
    public static final int DATABASE_VERSION = 1;

    public AllDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ALLLIST_TABLE = "CREATE TABLE " +
                AllEntry.TABLE_NAME + " (" +
                AllEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AllEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                AllEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                AllEntry.COLUMN_NOTE + " TEXT, " +
                AllEntry.COLUMN_DATE + " DATE DEFAULT CURRENT_DATE" +
                ");";
        db.execSQL(SQL_CREATE_ALLLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + AllEntry.TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<DataObject> getAllData(String s, String startD, String endD, String t) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        if (t == "" || t == null) {
            cursor = db.rawQuery("select * from "+ AllEntry.TABLE_NAME +
                    " where " + AllEntry.COLUMN_NOTE +
                    " like '%" + s + "%' and " +
                    AllEntry.COLUMN_DATE + " between " + startD + " and " + endD + ";", null);
        }
        else {
             cursor = db.rawQuery("select * from "+ AllEntry.TABLE_NAME +
                    " where " + AllEntry.COLUMN_NOTE +
                    " like '%" + s + "%' and " +
                    AllEntry.COLUMN_DATE + " between " + startD + " and " + endD + " and " +
                    AllEntry.COLUMN_TYPE + " like '%" + t + "';", null);
        }
        ArrayList<DataObject> objectList = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = Integer.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_ID)));
            String type = cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_TYPE));
            float cost = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            Date time = Date.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_DATE)));
            String note = cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_NOTE));
            DataObject obj = new DataObject(id, type, cost, time, note);
            objectList.add(obj);
        }
        return objectList;
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(
                AllEntry.TABLE_NAME,  // Where to delete
                AllEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});  // What to delete
        db.close();
    }

    public float getTotalFood() {
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Food"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalTransportation() {
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Transportation"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalStudy() {
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Study"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalHousing() {
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Housing"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalEntertainment() {
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Entertainment"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalClothing(){
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Clothing"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalCleaning(){
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Cleaning"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalPersonalCare() {
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"PersonalCare"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalHobby() {
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Hobby"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }

    public float getTotalOther() {
        SQLiteDatabase db = getReadableDatabase();
        String[] s = {"Other"};
        Cursor cursor = db.rawQuery("select " + AllEntry.COLUMN_PRICE + " from "+ AllEntry.TABLE_NAME +
                " where " + AllEntry.COLUMN_TYPE +
                " = ?", s);
        float total = 0;
        while(cursor.moveToNext()) {
            float price = Float.valueOf(cursor.getString(cursor.getColumnIndex(AllEntry.COLUMN_PRICE)));
            total += price;
        }
        return total;
    }
}
