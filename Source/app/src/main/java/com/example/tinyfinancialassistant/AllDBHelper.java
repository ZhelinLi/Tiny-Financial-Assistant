package com.example.tinyfinancialassistant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tinyfinancialassistant.AllContract.*;

import androidx.annotation.Nullable;

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
                AllEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AllEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                AllEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                AllEntry.COLUMN_NOTE + " TEXT, " +
                AllEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_ALLLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + AllEntry.TABLE_NAME);
        onCreate(db);
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
        String[] s = {"Clothping"};
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
