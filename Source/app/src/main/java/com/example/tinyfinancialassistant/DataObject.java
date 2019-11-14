package com.example.tinyfinancialassistant;

import java.io.Serializable;
import java.sql.Timestamp;

public class DataObject implements Serializable {
    private String type;
    private float cost;
    private Timestamp time;

    public DataObject(String type, float cost, Timestamp time) {
        this.type = type;
        this.cost = cost;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
