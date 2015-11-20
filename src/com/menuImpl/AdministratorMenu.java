package com.menuImpl;

import com.connection.ConnectionManager;
import com.constants.Constants;
import com.menu.Menu;
import com.operation.AdministrationOperation;
import com.utility.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by wong on 11/19/15.
 */
public class AdministratorMenu implements Menu {
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
        AdministrationOperation.createAllTable();
        System.out.println("Done! Database is initialized!");
    }

    public void deleteAllTable(){
        System.out.print("Processing...");
        AdministrationOperation.deleteAllTable();
        System.out.println("Done! Database is removed!");
    }

    public void loadFromDataFile(){
        Scanner input = new Scanner(System.in);
        System.out.print("Type in the Source Data Folder Path: ");
        String folderPath = input.nextLine();
        System.out.print("Processing...");
        AdministrationOperation.loadDataFromFile(folderPath);
        System.out.println("Done! Data is inputted to the database!");
    }

    public void showNumberOfRecord() {
        System.out.println("Number of records in each table:");
        HashMap<String,Integer> map = AdministrationOperation.showNumberOfRecord();
        System.out.println("category: " + map.get(Constants.TABLE_Category));
        System.out.println("manufacturer: " + map.get(Constants.TABLE_Manufacturer));
        System.out.println("part: " + map.get(Constants.TABLE_Part));
        System.out.println("salesperson: " + map.get(Constants.TABLE_SalesPerson));
        System.out.println("transaction: " + map.get(Constants.TABLE_Transaction));

    }

}
