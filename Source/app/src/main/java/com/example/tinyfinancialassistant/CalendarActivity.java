package com.example.tinyfinancialassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.savvi.rangedatepicker.CalendarPickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    CalendarPickerView calendar;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, - 10);

        calendar = findViewById(R.id.calendar_view);
        button = findViewById(R.id.get_selected_dates);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);

        //calendar.deactivateDates(list);
        final ArrayList<Date> arrayList = new ArrayList<>();
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

            String strdate = "22-4-2019";
            String strdate2 = "26-4-2019";

            Date newdate = dateformat.parse(strdate);
            Date newdate2 = dateformat.parse(strdate2);
            arrayList.add(newdate);
            arrayList.add(newdate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withHighlightedDates(arrayList);

        calendar.scrollToDate(new Date());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Date> primdates = calendar.getSelectedDates();
                List<String> dates = new ArrayList<>();
                for (Date date : primdates) {
                    String dateStr = dateToString(date);
                    dates.add(dateStr);
                }
                Intent intent = new Intent();
                intent.putExtra("Date", String.valueOf(dates));
                setResult(Activity.RESULT_OK,intent);
                finish();
                Log.d("date", String.valueOf(dates));
                //Toast.makeText(CalendarActivity.this, calendar.getSelectedDates().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public static String dateToString(Date date) {
        String convertedDate = "";

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            convertedDate = dateFormat.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertedDate;
    }

}