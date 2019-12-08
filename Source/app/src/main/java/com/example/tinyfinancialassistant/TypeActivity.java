package com.example.tinyfinancialassistant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TypeActivity extends AppCompatActivity {
    Button button_calender, homeButton, inputButton, listButton;
    TextView moneyText;
    AllDBHelper db;
    String lastDay, firstDay, defaultDay, today;
    HorizontalBarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        moneyText = (TextView) findViewById(R.id.type_money);

        homeButton = findViewById(R.id.homeButton);
        inputButton = findViewById(R.id.inputButton);
        listButton = findViewById(R.id.listButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TypeActivity.this, MainActivity.class));
            }
        });

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TypeActivity.this, InputActivity.class));
            }
        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TypeActivity.this, ListActivity.class));
            }
        });

        // HorizontalBarChart barChart = findViewById(R.id.barChart);
        barChart = findViewById(R.id.barChart);
        button_calender = findViewById(R.id.floating);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        today = "'" + df.format(c) + "'";
        if (lastDay == null || lastDay == "") lastDay = today;
        defaultDay = today.substring(0,9)  + "01" + "'";
        if (firstDay == null || firstDay == "") {
            firstDay = defaultDay;
        }

        db = new AllDBHelper(getApplicationContext());
        fillTextData(firstDay, lastDay);

        BarDataSet barDataSet = new BarDataSet(getData(), "Report");

        BarData barData = new BarData(barDataSet);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(false);
        barDataSet.setColors(Color.parseColor("#F44336"),
                Color.parseColor("#9C27B0"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#03A9F4"),
                Color.parseColor("#009688"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FFD600"),
                Color.parseColor("#FF9800"),
                Color.parseColor("#795548"),
                Color.parseColor("#607D8B"));


        final String[] types = new String[]{"Food", "Transportation", "Study", "Housing", "Entertainment", "Clothing", "Cleaning", "PersonalCare", "Hobby", "Other"};
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(types);
        xAxis.setValueFormatter(formatter);
        xAxis.setLabelCount(barDataSet.getEntryCount());
        barChart.setData(barData);
        barChart.animateXY(2000, 2000);
        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.invalidate();

        button_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TypeActivity.this, CalendarActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    private ArrayList getData(){
        db = new AllDBHelper(getApplicationContext());
        float foodCost = db.getTotalFood(defaultDay, lastDay);
        float transportationCost = db.getTotalTransportation(defaultDay, lastDay);
        float studyCost = db.getTotalStudy(defaultDay, lastDay);
        float housingCost = db.getTotalHousing(defaultDay, lastDay);
        float entertainmentCost = db.getTotalEntertainment(defaultDay, lastDay);
        float clothingCost = db.getTotalClothing(defaultDay, lastDay);
        float cleaningCost = db.getTotalCleaning(defaultDay, lastDay);
        float personalCareCost = db.getTotalPersonalCare(firstDay, lastDay);
        float hobbyCost = db.getTotalHobby(firstDay, lastDay);
        float otherCost = db.getTotalOther(firstDay, lastDay);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, foodCost));
        entries.add(new BarEntry(1f, transportationCost));
        entries.add(new BarEntry(2f, studyCost));
        entries.add(new BarEntry(3f, housingCost));
        entries.add(new BarEntry(4f, entertainmentCost));
        entries.add(new BarEntry(5f, clothingCost));
        entries.add(new BarEntry(6f, cleaningCost));
        entries.add(new BarEntry(7f, personalCareCost));
        entries.add(new BarEntry(8f, hobbyCost));
        entries.add(new BarEntry(9f, otherCost));

        return entries;
    }
    // This method is called when the calendar activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get String data from Intent
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String date =data.getStringExtra("Date");
                String temp = date.substring(1);
                date = temp.substring(0, date.length() - 2);
                String[] dateList = date.split(", ");
                firstDay = "'"+dateList[0]+"'";
                lastDay = "'"+dateList[dateList.length - 1]+"'";

                fillTextData(firstDay, lastDay);

                ArrayList<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry(0f, db.getTotalFood(firstDay, lastDay)));
                entries.add(new BarEntry(1f, db.getTotalTransportation(firstDay, lastDay)));
                entries.add(new BarEntry(2f, db.getTotalStudy(firstDay, lastDay)));
                entries.add(new BarEntry(3f, db.getTotalHousing(firstDay, lastDay)));
                entries.add(new BarEntry(4f, db.getTotalEntertainment(firstDay, lastDay)));
                entries.add(new BarEntry(5f, db.getTotalClothing(firstDay, lastDay)));
                entries.add(new BarEntry(6f, db.getTotalCleaning(firstDay, lastDay)));
                entries.add(new BarEntry(7f, db.getTotalPersonalCare(firstDay, lastDay)));
                entries.add(new BarEntry(8f, db.getTotalHobby(firstDay, lastDay)));
                entries.add(new BarEntry(9f, db.getTotalOther(firstDay, lastDay)));

                BarDataSet barDataSet = new BarDataSet(entries, "Report");

                BarData barData = new BarData(barDataSet);
                XAxis xAxis = barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setGranularity(1f);
                xAxis.setDrawGridLines(false);
                xAxis.setEnabled(true);
                xAxis.setDrawAxisLine(false);
                barDataSet.setColors(Color.parseColor("#F44336"),
                        Color.parseColor("#9C27B0"),
                        Color.parseColor("#3F51B5"),
                        Color.parseColor("#03A9F4"),
                        Color.parseColor("#009688"),
                        Color.parseColor("#8BC34A"),
                        Color.parseColor("#FFD600"),
                        Color.parseColor("#FF9800"),
                        Color.parseColor("#795548"),
                        Color.parseColor("#607D8B"));

                final String[] types = new String[]{"Food", "Transportation", "Study", "Housing", "Entertainment", "Clothing", "Cleaning", "PersonalCare", "Hobby", "Other"};
                IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(types);
                xAxis.setValueFormatter(formatter);
                xAxis.setLabelCount(barDataSet.getEntryCount());
                barChart.setData(barData);
                barChart.animateXY(2000, 2000);
                barChart.getLegend().setEnabled(false);
                barChart.getDescription().setEnabled(false);
                barChart.setFitBars(true);
                barChart.invalidate();
            }
        }
    }

    public void fillTextData(String StartD, String EndD) {
        float foodCost = db.getTotalFood(StartD, EndD);
        float transportationCost = db.getTotalTransportation(StartD, EndD);
        float studyCost = db.getTotalStudy(StartD, EndD);
        float housingCost = db.getTotalHousing(StartD, EndD);
        float entertainmentCost = db.getTotalEntertainment(StartD, EndD);
        float clothingCost = db.getTotalClothing(StartD, EndD);
        float cleaningCost = db.getTotalCleaning(StartD, EndD);
        float personalCareCost = db.getTotalPersonalCare(StartD, EndD);
        float hobbyCost = db.getTotalHobby(StartD, EndD);
        float otherCost = db.getTotalOther(StartD, EndD);
        float totalCost = foodCost + transportationCost + studyCost
                + housingCost + entertainmentCost + clothingCost
                + cleaningCost + personalCareCost + hobbyCost + otherCost;
        moneyText.setText(String.valueOf(totalCost));
    }
}

