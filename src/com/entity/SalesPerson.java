package com.entity;

/**
 * Created by wong on 11/19/15.
 */
public class SalesPerson {
    private int sID;
    private String sName;
    private String sAddress;
    private int sPhoneNumber;

    public SalesPerson(int sID, String sName, String sAddress, int sPhoneNumber) {
        this.sID = sID;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sPhoneNumber = sPhoneNumber;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public int getsPhoneNumber() {
        return sPhoneNumber;
    }

    public void setsPhoneNumber(int sPhoneNumber) {
        this.sPhoneNumber = sPhoneNumber;
    }
}
