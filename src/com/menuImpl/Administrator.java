package com.menuImpl;

import com.connection.ConnectionManager;
import com.constants.Constants;
import com.menu.Menu;
import com.utility.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by wong on 11/19/15.
 */
public class Administrator implements Menu {
    public static final int createAllTable = 1;
    public static final int deleteAllTable = 2;
    public static final int loadFromDataFile = 3;
    public static final int showNumberOfRecord = 4;
    public static final int returnToMain = 5;

    @Override
    public void printOperationMenu(){
        System.out.println("-----Operations for administrator menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Create all tables");
        System.out.println("2. Delete all tables");
        System.out.println("3. Load from datafile");
        System.out.println("4. Show number of records in each table");
        System.out.println("5. Return to the main menu");
        System.out.print("Enter Your Choice: ");
    }

    @Override
    public void mainOperation(Menu instance){
        while(true){
            System.out.println();
            instance.printOperationMenu();
            switch(Util.getChoice()){
                case createAllTable:
                    createAllTable();
                    break;
                case deleteAllTable:
                    deleteAllTable();
                    break;
                case loadFromDataFile:
                    loadFromDataFile();
                    break;
                case showNumberOfRecord:
                    showNumberOfRecord();
                    break;
                case returnToMain:
                    return;
                default:
                    System.out.println("No this choice. Please input again");
            }
        }
    }

    public void createAllTable(){
        System.out.print("Processing...");
        ConnectionManager cm = new ConnectionManager();
        cm.createAllTable();
        cm.closeConnection();
        System.out.println("Done! Database is initialized!");
    }

    public void deleteAllTable(){
        System.out.print("Processing...");
        ConnectionManager cm = new ConnectionManager();
        cm.deleteAllTable();
        cm.closeConnection();
        System.out.println("Done! Database is removed!");
    }

    public void loadFromDataFile(){
        Scanner input = new Scanner(System.in);
        System.out.print("Type in the Source Data Folder Path: ");
        String folderPath = input.nextLine();
        System.out.print("Processing...");
        ConnectionManager cm = new ConnectionManager();
        cm.loadDataFromFile(folderPath);
        cm.closeConnection();
        System.out.println("Done! Data is inputted to the database!");
    }

    public void showNumberOfRecord() {
        System.out.println("Number of records in each table:");
        int cNum = 0;
        int mNum = 0;
        int pNum = 0;
        int sNum = 0;
        int tNum = 0;
        ResultSet rs = null;
        ConnectionManager cm = new ConnectionManager();


        try {
            rs = cm.getQueryResult("SELECT COUNT(*) AS rowcount FROM Category");
            rs.next();
            cNum = rs.getInt("rowcount");
            rs.close();

            rs = cm.getQueryResult("SELECT COUNT(*) AS rowcount FROM Manufacturer");
            rs.next();
            mNum = rs.getInt("rowcount");
            rs.close();

            rs = cm.getQueryResult("SELECT COUNT(*) AS rowcount FROM Part");
            rs.next();
            pNum = rs.getInt("rowcount");
            rs.close();

            rs = cm.getQueryResult("SELECT COUNT(*) AS rowcount FROM SalesPerson");
            rs.next();
            sNum = rs.getInt("rowcount");
            rs.close();

            rs = cm.getQueryResult("SELECT COUNT(*) AS rowcount FROM Transaction");
            rs.next();
            tNum = rs.getInt("rowcount");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Fail to count the number of each table");
        }
        cm.closeConnection();

        System.out.println("category: " + cNum);
        System.out.println("manufacturer: " + mNum);
        System.out.println("part: " + pNum);
        System.out.println("salesperson: " + sNum);
        System.out.println("transaction: " + tNum);

    }

}
