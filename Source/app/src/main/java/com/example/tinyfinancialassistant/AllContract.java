package com.example.tinyfinancialassistant;
import android.provider.BaseColumns;

public class AllContract {

    private AllContract() {
    }

    public static final class AllEntry implements BaseColumns {
        public static final String TABLE_NAME = "allList";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_DATE = "date";
    }
}