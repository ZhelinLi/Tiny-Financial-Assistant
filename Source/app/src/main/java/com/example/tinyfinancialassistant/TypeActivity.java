package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TypeActivity extends AppCompatActivity {
    Button button_calender;
    TextView moneyText;
    AllDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        moneyText = (TextView) findViewById(R.id.type_money);

        HorizontalBarChart barChart = findViewById(R.id.barChart);
        button_calender = findViewById(R.id.floating);

        db = new AllDBHelper(getApplicationContext());
        float foodCost = db.getTotalFood();
        float transportationCost = db.getTotalTransportation();
        float studyCost = db.getTotalStudy();
        float housingCost = db.getTotalHousing();
        float entertainmentCost = db.getTotalEntertainment();
        float clothingCost = db.getTotalClothing();
        float cleaningCost = db.getTotalCleaning();
        float personalCareCost = db.getTotalPersonalCare();
        float hobbyCost = db.getTotalHobby();
        float otherCost = db.getTotalOther();
        float totalCost = foodCost + transportationCost + studyCost
                + housingCost + entertainmentCost + clothingCost
                + cleaningCost + personalCareCost + hobbyCost + otherCost;
        moneyText.setText(String.valueOf(totalCost));

        BarDataSet barDataSet = new BarDataSet(getData(), "Report");

        BarData barData = new BarData(barDataSet);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(false);


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
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        button_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TypeActivity.this, CalendarActivity.class));
            }
        });
    }

    private ArrayList getData(){
        db = new AllDBHelper(getApplicationContext());
        float foodCost = db.getTotalFood();
        float transportationCost = db.getTotalTransportation();
        float studyCost = db.getTotalStudy();
        float housingCost = db.getTotalHousing();
        float entertainmentCost = db.getTotalEntertainment();
        float clothingCost = db.getTotalClothing();
        float cleaningCost = db.getTotalCleaning();
        float personalCareCost = db.getTotalPersonalCare();
        float hobbyCost = db.getTotalHobby();
        float otherCost = db.getTotalOther();

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
}

