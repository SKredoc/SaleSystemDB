package com.menu;

import com.utility.Util;

import java.util.Scanner;

/**
 * Created by wong on 11/19/15.
 */
public class Administrator implements Menu{
    public static final int createAllTable = 1;
    public static final int deleteAllTable = 2;
    public static final int loadFromDataFile = 3;
    public static final int showNumberOfRecord = 4;
    public static final int returnToMain = 5;

    @Override
    public void printOperationMenu(){
        System.out.println("----Operations for administrator menu----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Create all tables");
        System.out.println("2. Delete all tables");
        System.out.println("3. Load from datafile");
        System.out.println("4. Show number of records in each table");
        System.out.println("5. Return to the main menu");
        System.out.println("Enter Your Choice: ");
    }

    @Override
    public void mainOperation(Menu instance){
        while(true){
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

    public static void createAllTable(){
        System.out.print("Processing...");
        //TODO create all table
        System.out.println("Done! Database is initialized!");
    }

    public static void deleteAllTable(){
        System.out.print("Processing...");
        //TODO delete all table
        System.out.println("Done! Database is removed!");
    }

    public static void loadFromDataFile(){
        Scanner input = new Scanner(System.in);
        System.out.print("Type in the Source Data Folder Path: ");
        String path = input.nextLine();
        System.out.print("Processing...");
        //TODO load from data file
        System.out.println("Done! Data is inputted to the database!");
    }

    public static void showNumberOfRecord() {
        System.out.println("Number of records in each table:");
        //TODO show number of record
    }

}
