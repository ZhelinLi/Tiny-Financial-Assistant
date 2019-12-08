package com.example.tinyfinancialassistant;

import java.io.Serializable;
import java.sql.Date;

public class DataObject implements Serializable {
    private int id;
    private String type;
    private float cost;
    private Date time;
    private String note;

    public DataObject(int id, String type, float cost, Date time, String note) {
        this.id = id;
        this.type = type;
        this.cost = cost;
        this.time = time;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
