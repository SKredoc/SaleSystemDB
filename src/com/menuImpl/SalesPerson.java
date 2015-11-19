package com.menuImpl;

import com.connection.ConnectionManager;
import com.menu.Menu;
import com.utility.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by wong on 11/19/15.
 */
public class SalesPerson implements Menu {
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
        ConnectionManager cm = null;
        ResultSet rs = null;

        cm = new ConnectionManager();
        rs = cm.getQueryResult("SELECT P.pName, P.pAvailableQuantity FROM Part P WHERE P.pID = " + this.pID);
        try {
            rs.next();
            String pName = rs.getString("pName");
            int pQuantity = rs.getInt("pAvailableQuantity");
            rs.close();
            if(pQuantity > 0){
                System.out.println("Product: " + pName + "(id: " + this.pID + ") Remaining Quality: " + (pQuantity-1));
                int maxtID = 0; //TODO is the tID increase in ascending order?
                rs = cm.getQueryResult("SELECT MAX(T.tID) FROM Transaction T");
                rs.next();
                maxtID =  rs.getInt(1);
                rs.close();
                System.out.println("The maximum of tid is " + maxtID); //TODO delete
                cm.updateQuery("INSERT INTO Transaction VALUES (" + (maxtID+1) + "," + this.pID + "," + this.sID + "," + Util.getCurrentDate() + ")");
            }
            else{
                System.out.println("Error: Not enough Quality of Product: " + pName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cm.closeConnection();
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
                    search(pattern, "ASC");
                    return;
                case byPriceDESC:
                    pattern = "P.pName LIKE '_%" + this.partName +"_%' ";
                    search(pattern, "DESC");
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
                    search(pattern, "ASC");
                    return;
                case byPriceDESC:
                    pattern = "M.mName LIKE '_%" + this.manuName +"_%' ";
                    search(pattern, "DESC");
                    return;
                default:
                    System.out.println("No this choice. Please enter again.");
            }
        }
   }

    public void search(String pattern, String order){
        ConnectionManager cm;
        ResultSet rs;

        cm = new ConnectionManager();
        rs = cm.getQueryResult("SELECT P.pID, P.pName, M.mName, P.cID, P.pAvailableQuantity, M.mWarrantyPeriod, P.pPrice " +
                "FROM Part P, Manufacturer M " +
                "WHERE P.mID = M.mID AND " + pattern +
                " ORDER BY " + order);
        System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
        try {
            while(rs.next()){
                System.out.println("| " + rs.getInt("pID") + " | " + rs.getString("pName") + " | " + rs.getString("mName") + " | " + rs.getInt("cID") + " | " +
                        rs.getInt("pAvailableQuantity") + " | " + rs.getInt("mWarrantyPeriod") + " | " + rs.getInt("pPrice" + " |"));
            }
        } catch (SQLException e) {
            System.out.println("Fail to search");
        }
        cm.closeConnection();
        System.out.println("End of Query");
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
