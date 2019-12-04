package com.example.tinyfinancialassistant;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button inputButton, typeButton, listButton;
    TextView moneyText, studyMoney, transportationMoney, clothingMoney, housingMoney, entertainmentMoney, hobbyMoney, foodMoeny,
    personalMoney, cleaningMoney, otherMoney;
    AllDBHelper db;
    String today = "'2019-12-04'";
    // today = "'" + df.format(c) + "'";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputButton = findViewById(R.id.inputButton);
        typeButton = findViewById(R.id.typeButton);
        listButton = findViewById(R.id.listButton);
        moneyText = (TextView) findViewById(R.id.money);
        studyMoney = findViewById(R.id.study_money);
        transportationMoney = findViewById(R.id.transport_money);
        clothingMoney = findViewById(R.id.clothing_money);
        housingMoney = findViewById(R.id.housing_money);
        entertainmentMoney = findViewById(R.id.entertain_money);
        hobbyMoney = findViewById(R.id.hobby_money);
        foodMoeny = findViewById(R.id.food_money);
        personalMoney = findViewById(R.id.personal_money);
        cleaningMoney = findViewById(R.id.cleaning_money);
        otherMoney = findViewById(R.id.other_money);

        db = new AllDBHelper(getApplicationContext());
        float foodCost = db.getTotalFood(today, today);
        float transportationCost = db.getTotalTransportation(today, today);
        float studyCost = db.getTotalStudy(today, today);
        float housingCost = db.getTotalHousing(today, today);
        float entertainmentCost = db.getTotalEntertainment(today, today);
        float clothingCost = db.getTotalClothing(today, today);
        float cleaningCost = db.getTotalCleaning(today, today);
        float personalCareCost = db.getTotalPersonalCare(today, today);
        float hobbyCost = db.getTotalHobby(today, today);
        float otherCost = db.getTotalOther(today, today);
        float totalCost = foodCost + transportationCost + studyCost
                + housingCost + entertainmentCost + clothingCost
                + cleaningCost + personalCareCost + hobbyCost + otherCost;
        moneyText.setText(String.valueOf(totalCost));
        studyMoney.setText("$"+ String.valueOf(studyCost));
        transportationMoney.setText("$"+ String.valueOf(transportationCost));
        foodMoeny.setText("$"+ String.valueOf(foodCost));
        housingMoney.setText("$"+ String.valueOf(housingCost));
        entertainmentMoney.setText("$"+ String.valueOf(entertainmentCost));
        clothingMoney.setText("$"+ String.valueOf(clothingCost));
        cleaningMoney.setText("$"+ String.valueOf(cleaningCost));
        personalMoney.setText("$"+ String.valueOf(personalCareCost));
        hobbyMoney.setText("$"+ String.valueOf(hobbyCost));
        otherMoney.setText("$"+ String.valueOf(otherCost));

        PieChart pieChart = findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(60);
        pieChart.setTransparentCircleRadius(65f);
        pieChart.setRotationEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setTouchEnabled(true);

        ArrayList<PieEntry> dollars = new ArrayList<>();
        if (foodCost != 0.0) {
            dollars.add(new PieEntry(foodCost, "Food"));
        }
        if (transportationCost != 0.0) {
            dollars.add(new PieEntry(transportationCost, "Transportation"));
        }
        if (studyCost != 0.0) {
            dollars.add(new PieEntry(studyCost, "Study"));
        }
        if (housingCost != 0.0) {
            dollars.add(new PieEntry(housingCost, "Housing"));
        }
        if (entertainmentCost != 0.0) {
            dollars.add(new PieEntry(entertainmentCost, "Entertainment"));
        }
        if (clothingCost != 0.0) {
            dollars.add(new PieEntry(clothingCost, "Clothing"));
        }
        if (cleaningCost != 0.0) {
            dollars.add(new PieEntry(cleaningCost, "Cleaning"));
        }
        if (personalCareCost != 0.0) {
            dollars.add(new PieEntry(personalCareCost, "Personal Care"));
        }
        if (hobbyCost != 0.0) {
            dollars.add(new PieEntry(hobbyCost, "Hobbies"));
        }
        if (otherCost != 0.0) {
            dollars.add(new PieEntry(otherCost, "Other"));
        }

        PieDataSet dataSet = new PieDataSet(dollars, "Type");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(Color.parseColor("#F44336"),
                Color.parseColor("#9C27B0"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#03A9F4"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#009688"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FFD600"),
                Color.parseColor("#FF9800"),
                Color.parseColor("#795548"),
                Color.parseColor("#607D8B"));
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
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();

        moneyText = (TextView) findViewById(R.id.money);
        studyMoney = findViewById(R.id.study_money);
        transportationMoney = findViewById(R.id.transport_money);
        clothingMoney = findViewById(R.id.clothing_money);
        housingMoney = findViewById(R.id.housing_money);
        entertainmentMoney = findViewById(R.id.entertain_money);
        hobbyMoney = findViewById(R.id.hobby_money);
        foodMoeny = findViewById(R.id.food_money);
        personalMoney = findViewById(R.id.personal_money);
        cleaningMoney = findViewById(R.id.cleaning_money);
        otherMoney = findViewById(R.id.other_money);

        db = new AllDBHelper(getApplicationContext());
        float foodCost = db.getTotalFood(today, today);
        float transportationCost = db.getTotalTransportation(today, today);
        float studyCost = db.getTotalStudy(today, today);
        float housingCost = db.getTotalHousing(today, today);
        float entertainmentCost = db.getTotalEntertainment(today, today);
        float clothingCost = db.getTotalClothing(today, today);
        float cleaningCost = db.getTotalCleaning(today, today);
        float personalCareCost = db.getTotalPersonalCare(today, today);
        float hobbyCost = db.getTotalHobby(today, today);
        float otherCost = db.getTotalOther(today, today);
        float totalCost = foodCost + transportationCost + studyCost
                + housingCost + entertainmentCost + clothingCost
                + cleaningCost + personalCareCost + hobbyCost + otherCost;
        moneyText.setText(String.valueOf(totalCost));
        moneyText.setText(String.valueOf(totalCost));
        studyMoney.setText(String.valueOf(studyCost));
        transportationMoney.setText(String.valueOf(transportationCost));
        foodMoeny.setText(String.valueOf(foodCost));
        housingMoney.setText(String.valueOf(housingCost));
        entertainmentMoney.setText(String.valueOf(entertainmentCost));
        clothingMoney.setText(String.valueOf(clothingCost));
        cleaningMoney.setText(String.valueOf(cleaningCost));
        personalMoney.setText(String.valueOf(personalCareCost));
        hobbyMoney.setText(String.valueOf(hobbyCost));
        otherMoney.setText(String.valueOf(otherCost));

        PieChart pieChart = findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setRotationEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setTouchEnabled(true);

        ArrayList<PieEntry> dollars = new ArrayList<>();
        if (foodCost != 0.0) {
            dollars.add(new PieEntry(foodCost, "Food"));
        }
        if (transportationCost != 0.0) {
            dollars.add(new PieEntry(transportationCost, "Transportation"));
        }
        if (studyCost != 0.0) {
            dollars.add(new PieEntry(studyCost, "Study"));
        }
        if (housingCost != 0.0) {
            dollars.add(new PieEntry(housingCost, "Housing"));
        }
        if (entertainmentCost != 0.0) {
            dollars.add(new PieEntry(entertainmentCost, "Entertainment"));
        }
        if (clothingCost != 0.0) {
            dollars.add(new PieEntry(clothingCost, "Clothing"));
        }
        if (cleaningCost != 0.0) {
            dollars.add(new PieEntry(cleaningCost, "Cleaning"));
        }
        if (personalCareCost != 0.0) {
            dollars.add(new PieEntry(personalCareCost, "Personal Care"));
        }
        if (hobbyCost != 0.0) {
            dollars.add(new PieEntry(hobbyCost, "Hobbies"));
        }
        if (otherCost != 0.0) {
            dollars.add(new PieEntry(otherCost, "Other"));
        }


        PieDataSet dataSet = new PieDataSet(dollars, "Type");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(Color.parseColor("#F44336"),
                Color.parseColor("#9C27B0"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#03A9F4"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#009688"),
                Color.parseColor("#8BC34A"),
                Color.parseColor("#FFD600"),
                Color.parseColor("#FF9800"),
                Color.parseColor("#795548"),
                Color.parseColor("#607D8B"));
        pieChart.animateY(2000, Easing.EaseInOutCubic);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.YELLOW);
        pieChart.setData(data);
    }

}
