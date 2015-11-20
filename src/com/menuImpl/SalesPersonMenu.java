package com.menuImpl;

import com.connection.ConnectionManager;
import com.menu.Menu;
import com.operation.SalesPersonOperation;
import com.utility.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by wong on 11/19/15.
 */
public class SalesPersonMenu implements Menu {
    public static final int searchForParts = 1;
    public static final int sellAParts = 2;
    public static final int returnToMainMenu = 3;

    //searchForParts criterion
    public static final int searchPartName = 1;
    public static final int searchManuName = 2;

    //criterion ordering
    public static final int byPriceASCE = 1;
    public static final int byPriceDESC = 2;

    private String partName;
    private String manuName;
    private int pID;
    private int sID;

    @Override
    public void printOperationMenu() {
        System.out.println("-----Operations for salesperson menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Search for parts");
        System.out.println("2. Sell a parts");
        System.out.println("3. Return to the main menu");
        System.out.print("Enter Your Choice: ");
    }

    @Override
    public void mainOperation(Menu instance) {
        while (true) {
            System.out.println();
            instance.printOperationMenu();
            switch (Util.getChoice()) {
                case searchForParts:
                    searchForParts();
                    break;
                case sellAParts:
                    sellAParts();
                    break;
                case returnToMainMenu:
                    return;
                default:
                    System.out.println("No this choice. Please enter again.");
            }
        }
    }

    public void searchForParts() {
        while (true) {
            printSearchCriterionMenu();
            switch (Util.getChoice()) {
                case searchPartName:
                    searchPartName();
                    return;
                case searchManuName:
                    searchManuName();
                    return;
                default:
                    System.out.println("No this choice. Please enter again.");
            }
        }
    }

    public void sellAParts() {
        System.out.print("Enter The Part ID: ");
        this.pID = Util.getChoice();
        System.out.print("Enter The Salesperson ID: ");
        this.sID = Util.getChoice();
        SalesPersonOperation.sellAPart(this.pID, this.sID);
    }

    public void searchPartName() {
        while (true) {
            System.out.print("Type in the Search Keyword:");
            Scanner scanner = new Scanner(System.in);
            this.partName = scanner.nextLine();
            printOrderingMenu();
            String pattern = null;
            switch (Util.getChoice()) {
                case byPriceASCE:
                    pattern = "P.pName LIKE '_%" + this.partName +"_%' ";
                    SalesPersonOperation.search(pattern, "ASC");
                    return;
                case byPriceDESC:
                    pattern = "P.pName LIKE '_%" + this.partName +"_%' ";
                    SalesPersonOperation.search(pattern, "DESC");
                    return;
                default:
                    System.out.println("No this choice. Please enter again.");
            }
        }
    }

    public void searchManuName() {
        while (true) {
            System.out.print("Type in the Search Keyword:");
            Scanner scanner = new Scanner(System.in);
            this.manuName = scanner.nextLine();
            printOrderingMenu();
            String pattern = null;
            switch (Util.getChoice()) {
                case byPriceASCE:
                    pattern = "M.mName LIKE '_%" + this.manuName +"_%' ";
                    SalesPersonOperation.search(pattern, "ASC");
                    return;
                case byPriceDESC:
                    pattern = "M.mName LIKE '_%" + this.manuName +"_%' ";
                    SalesPersonOperation.search(pattern, "DESC");
                    return;
                default:
                    System.out.println("No this choice. Please enter again.");
            }
        }
   }

    public void printSearchCriterionMenu() {
        System.out.println("Choose the Search criterion:");
        System.out.println("1. Part Name");
        System.out.println("2. Manufacturer Name");
        System.out.print("Choose the search criterion: ");
    }

    public void printOrderingMenu() {
        System.out.println("Choose ordering:");
        System.out.println("1. By price, ascending order");
        System.out.println("2. By price, descending order");
        System.out.print("Choose the search criterion: ");
    }

}
