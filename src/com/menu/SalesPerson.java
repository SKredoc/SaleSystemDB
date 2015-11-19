package com.menu;

/**
 * Created by wong on 11/19/15.
 */
public class SalesPerson implements Menu{
    public static int searchForParts = 1;
    public static int sellAParts = 2;
    public static int returnToMainMenu = 3;

    //searchForParts criterion
    public static int searchPartName = 1;
    public static int searchManuName = 2;

    //criterion ordering
    public static int byPriceASCE = 1;
    public static int byPriceDESC = 2;

    @Override
    public void printOperationMenu(){
        System.out.println("-----Operations for salesperson menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Search for parts");
        System.out.println("2. Sell a parts");
        System.out.println("3. Return to the main menu");
        System.out.println("Enter Your Choice: ");
    }

    @Override
    public void mainOperation(Menu instance) {

    }

    public static void printSearchCriterionMenu(){
        System.out.println("Choose the Search criterion:");
        System.out.println("1. Part Name");
        System.out.println("2. Manufacturer Name");
        System.out.println("Choose the search criterion: ");
    }

    public static void printOrderingMenu(){
        System.out.println("Choose ordering:");
        System.out.println("1. By price, ascending order");
        System.out.println("2. By price, descending order");
        System.out.println("Choose the search criterion: ");
    }
    public static void searchForParts(){
        SalesPerson.printSearchCriterionMenu();

    }

    public static void sellAParts(){

    }

    public static void returnToMainMenu(){

    }


}
