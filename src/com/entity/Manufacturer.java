package com.entity;

/**
 * Created by wong on 11/19/15.
 */
public class Manufacturer {
    private int mID;
    private String mName;
    private String mAddress;
    private int mPhoneNumber;
    private int mWarrantyPeriod;

    public Manufacturer(int mID, String mName, String mAddress, int mPhoneNumber, int mWarrantyPeriod) {
        this.mID = mID;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
        this.mWarrantyPeriod = mWarrantyPeriod;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public int getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(int mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public int getmWarrantyPeriod() {
        return mWarrantyPeriod;
    }

    public void setmWarrantyPeriod(int mWarrantyPeriod) {
        this.mWarrantyPeriod = mWarrantyPeriod;
    }
}
