package com.example.tinyfinancialassistant;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalenderActivity extends AppCompatActivity {
    //CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

       /* calendarView = findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month+1) + "/" + dayOfMonth + "/" + year;
                Toast.makeText(CalenderActivity.this, date, Toast.LENGTH_SHORT).show();
            }
        });*/
        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView datepicker = findViewById(R.id.calendar);
        datepicker.init(today, nextYear.getTime()).withSelectedDate(today)
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(today);

        List<Date> dates = datepicker.getSelectedDates();// This line has error
        Log.d("Date", dates.toString());

        datepicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.setTime(date);
                /*String selectedDate = "" + selectedCalendar.get(Calendar.DAY_OF_MONTH)
                        + " " + (selectedCalendar.get(Calendar.MONTH) + 1)
                        + " " + selectedCalendar.get(Calendar.YEAR);*/
                //Toast.makeText(CalenderActivity.this, selectedDate, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }
}
