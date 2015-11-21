package com.entity;

import java.sql.Date;

/**
 * Created by wong on 11/19/15.
 */
public class Transaction {
    private int tID;
    private int pID;
    private int sID;
    private String date;

    public Transaction(int tID, int pID, int sID, String date) {
        this.tID = tID;
        this.pID = pID;
        this.sID = sID;
        this.date = date;
    }

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
