package com.menu;

import com.utility.Util;

import java.sql.Date;
import java.util.Scanner;

/**
 * Created by wong on 11/19/15.
 */
public class Manager implements Menu {
    public static final int showSalesRecord = 1;
    public static final int showSalesValue = 2;
    public static final int showPopularPart = 3;
    public static final int returnToMain = 4;

    private int sID;
    private Date startDate;
    private Date endDate;

    @Override
    public void printOperationMenu(){
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Show the sales record of a salesperson within a period");
        System.out.println("2. Show the total sales value of each manufacturer");
        System.out.println("3. Show the N most popular part");
        System.out.println("4. Return to the main menu");
        System.out.println("Enter Your Choice: ");
    }

    @Override
    public void mainOperation(Menu instance) {
        while(true){
            instance.printOperationMenu();
            switch (Util.getChoice()){
                case showSalesRecord:
                    instance.showSalesRecord();
                    break;
                case showSalesValue:
                    showSalesRecord();
                    break;
                case showPopularPart:
                    showPopularPart();
                    break;
                case returnToMain:
                    return;
                default:
                    System.out.println("No this choice. Please input again");
            }
        }
    }

    public void showSalesRecord(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter The Salesperson ID: ");
        this.sID = input.nextInt();

        System.out.print("Type in the starting date [dd/mm/yyyy]: ");
        String startDate = input.nextLine();
        //TODO change date to Date format

        System.out.print("Type in the ending date [dd/mm/yyyy]: ");
        String endDate = input.nextLine();
        //TODO change date to Date format

        System.out.println("Transcation Record:");
        //TODO show sales record

    }

    public void showSalesValue(){

    }

    public void showPopularPart(){

    }

}
