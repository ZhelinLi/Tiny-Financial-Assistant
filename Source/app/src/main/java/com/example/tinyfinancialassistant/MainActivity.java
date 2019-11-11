package com.example.tinyfinancialassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button inputButton, typeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputButton = findViewById(R.id.inputButton);
        typeButton = findViewById(R.id.typeButton);

        PieChart pieChart = findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setRotationEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setTouchEnabled(true);

        ArrayList<PieEntry> dollars = new ArrayList<>();
        dollars.add(new PieEntry(350f, "Food"));
        dollars.add(new PieEntry(200f, "Transportation"));
        dollars.add(new PieEntry(900f, "Housing"));
        dollars.add(new PieEntry(1240f, "Study"));
        dollars.add(new PieEntry(436f, "Clothing"));
        dollars.add(new PieEntry(248f, "Personal Care"));
        dollars.add(new PieEntry(56f, "Cleaning"));
        dollars.add(new PieEntry(64f, "Hobbies"));
        dollars.add(new PieEntry(278f, "Entertainment"));
        dollars.add(new PieEntry(156f, "Other"));

        PieDataSet dataSet = new PieDataSet(dollars, "Type");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieChart.animateY(2000, Easing.EaseInOutCubic);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.YELLOW);
        pieChart.setData(data);

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InputActivity.class));
            }
        });

        typeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TypeActivity.class));
            }
        });
    }

}