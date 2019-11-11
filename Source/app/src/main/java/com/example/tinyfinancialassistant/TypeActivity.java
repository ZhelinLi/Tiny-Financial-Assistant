package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
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
    FloatingActionButton button_calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        HorizontalBarChart barChart = findViewById(R.id.barChart);
        button_calender = findViewById(R.id.floating);
        BarDataSet barDataSet = new BarDataSet(getData(), "Report");

        BarData barData = new BarData(barDataSet);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(false);


        final String[] types = new String[]{"Food", "Transportation", "Housing", "Study", "Clothing", "Personal Care", "Cleaning", "Hobbies", "Entertainment", "Other"};
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
                startActivity(new Intent(TypeActivity.this, CalenderActivity.class));
            }
        });
    }

    private ArrayList getData(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 350f));
        entries.add(new BarEntry(1f, 200f));
        entries.add(new BarEntry(2f, 900f));
        entries.add(new BarEntry(3f, 1240f));
        entries.add(new BarEntry(4f, 436f));
        entries.add(new BarEntry(5f, 248f));
        entries.add(new BarEntry(6f, 56f));
        entries.add(new BarEntry(7f, 64f));
        entries.add(new BarEntry(8f, 278f));
        entries.add(new BarEntry(9f, 156f));
        return entries;
    }
}

