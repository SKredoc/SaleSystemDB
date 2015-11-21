package com.utility;

import com.entity.*;
import com.sun.org.apache.bcel.internal.generic.INEG;

import java.io.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by wong on 11/19/15.
 */
public class Util {
    public static int getChoice(){
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        int choice = 0;
        while(true){
            try{
                input = stdin.readLine();
            } catch (IOException e) {
                System.out.println("CSCI3170Proj.getChoice: IOException");
            }
            try{
                choice = Integer.parseInt(input);
                return choice;
            } catch (NumberFormatException nfe){
                System.out.println("It is not a valid number. Please enter again: ");
            }
        }
    }

    public static BufferedReader getTextFileReader(String folderPath, String fileName){
        try {
            return new BufferedReader(new FileReader(new File("../" + folderPath + fileName)));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to read File = [../" + folderPath+fileName +"]");
        }
        return null;
    }

    public static ArrayList<Category> getCategoryList(BufferedReader br){
        if(br == null)
            return null;

        ArrayList<Category> categoryList = new ArrayList<Category>();
        String line = null;
        int cID;
        String cName = null;
        try {
            line = br.readLine();
            while(line != null){
                String[] splitStr = line.split("\t");
                if(splitStr.length == 2) {
                    cID = Integer.parseInt(splitStr[0]);
                    cName = splitStr[1];
                    categoryList.add(new Category(cID, cName));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Fail to get CategoryList");
        }
        return categoryList;
    }

    public static ArrayList<Manufacturer> getManufacturerList(BufferedReader br){
        if(br == null)
            return null;

        ArrayList<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();
        String line = null;
        int mID;
        String mName = null;
        String mAddress = null ;
        int mPhoneNumber;
        int mWarrantyPeriod;

        try {
            line = br.readLine();
            while(line != null){
                String[] splitStr = line.split("\t");
                if(splitStr.length == 5){
                    mID = Integer.parseInt(splitStr[0]);
                    mName = splitStr[1];
                    mAddress = splitStr[2];
                    mPhoneNumber = Integer.parseInt(splitStr[3]);
                    mWarrantyPeriod = Integer.parseInt(splitStr[4]);
                    manufacturerList.add(new Manufacturer(mID, mName, mAddress, mPhoneNumber, mWarrantyPeriod));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Fail to get manufacturerList");
        }
        return manufacturerList;
    }

    public static ArrayList<Part> getPartList(BufferedReader br){
        if(br == null)
            return null;

        ArrayList<Part> partList = new ArrayList<Part>();
        String line = null;
        int pID;
        String pName = null;
        int pPrice;
        int mID;
        int cID;
        int pAvailableQuantity;

        try {
            line = br.readLine();
            while(line != null){
                String[] splitStr = line.split("\t");
                if(splitStr.length == 6){
                    pID = Integer.parseInt(splitStr[0]);
                    pName = splitStr[1];
                    pPrice = Integer.parseInt(splitStr[2]);
                    mID = Integer.parseInt(splitStr[3]);
                    cID = Integer.parseInt(splitStr[4]);
                    pAvailableQuantity = Integer.parseInt(splitStr[5]);
                    partList.add(new Part(pID, pName, pPrice, mID, cID, pAvailableQuantity));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Fail to get partList");
        }
        return partList;
    }

    public static ArrayList<SalesPerson> getSalesPersonList(BufferedReader br){
        if(br == null)
            return null;

        ArrayList<SalesPerson> salesPersonsList = new ArrayList<SalesPerson>();
        String line = null;
        int sID;
        String sName = null;
        String sAddress = null;
        int sPhoneNumber;

        try {
            line = br.readLine();
            while(line != null){
                String[] splitStr = line.split("\t");
                if(splitStr.length == 4){
                    sID = Integer.parseInt(splitStr[0]);
                    sName = splitStr[1];
                    sAddress = splitStr[2];
                    sPhoneNumber = Integer.parseInt(splitStr[3]);
                    salesPersonsList.add(new SalesPerson(sID, sName, sAddress, sPhoneNumber));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Fail to get SalesPersonList");
        }
        return salesPersonsList;
    }

    public static ArrayList<Transaction> getTransactionList(BufferedReader br){
        if(br == null)
            return null;

        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        String line = null;
        int tID;
        int pID;
        int sID;
        String date;
        try {
            line = br.readLine();
            while(line != null){
                String[] splitStr = line.split("\t");
                if(splitStr.length == 4){
                    tID = Integer.parseInt(splitStr[0]);
                    pID = Integer.parseInt(splitStr[1]);
                    sID = Integer.parseInt(splitStr[2]);
                    date = splitStr[3];
                    transactionList.add(new Transaction(tID, pID, sID, date));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Fail to get TransactionList");
        }
        return transactionList;
    }

    public static String changeDateFormat(String dateStr){
        SimpleDateFormat old_format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat new_format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return new_format.format(old_format.parse(dateStr));
        } catch (ParseException e) {
            System.out.println("Fail to change the format of date");
            return "";
        }

    }

    public static String getCurrentDate(){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date = new java.util.Date();
        return format.format(date);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

}
