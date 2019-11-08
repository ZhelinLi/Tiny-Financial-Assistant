package com.example.tinyfinancialassistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
}
