package com.menuImpl;

import com.connection.ConnectionManager;
import com.menu.Menu;
import com.utility.Util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wong on 11/19/15.
 */
public class MainMenu implements Menu {
    public static final int administrator = 1;
    public static final int salesPerson = 2;
    public static final int manager = 3;
    public static final int exit = 4;

    public static final int viewAllTable = 5;
    @Override
    public void printOperationMenu() {
        System.out.println();
        System.out.println("-----Main menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Operations for administrator");
        System.out.println("2. Operations for salesperson");
        System.out.println("3. Operations for manager");
        System.out.println("4. Exit this program");
        System.out.print("Enter Your Choice: ");
    }

    @Override
    public void mainOperation(Menu instance) {
        while(true){
            instance.printOperationMenu();
            switch (Util.getChoice()){
                case MainMenu.administrator:
                    AdministratorMenu admin = new AdministratorMenu();
                    admin.mainOperation(admin);
                    break;
                case MainMenu.salesPerson:
                    SalesPersonMenu salesPerson = new SalesPersonMenu();
                    salesPerson.mainOperation(salesPerson);
                    break;
                case MainMenu.manager:
                    ManagerMenu manager = new ManagerMenu();
                    manager.mainOperation(manager);
                    break;
                case MainMenu.exit:
                    return;
                //case MainMenu.viewAllTable:
                //    try {
                //        MainMenu.viewAllTable();
                //    } catch (SQLException e) {
                //       e.printStackTrace();
                //    }
                //    break;
                default:
                    System.out.println("No this choice. Please input again");
            }
        }
    }

    public static void viewAllTable() throws SQLException {
        ConnectionManager cm;
        ResultSet rs;

        String c = "SELECT * FROM Category";
        String m = "SELECT * FROM Manufacturer";
        String p = "SELECT * FROM Part";
        String s = "SELECT * FROM SalesPerson";
        String t = "SELECT * FROM Transaction";

        cm = new ConnectionManager();
        rs = cm.getQueryResult(c);
        if(rs==null) return;
        while(rs.next()){
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
        }

        rs = cm.getQueryResult(m);
        if(rs==null) return;
        while(rs.next()){
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+
                    "\t"+rs.getInt(4)+"\t"+rs.getInt(5));
        }

        rs = cm.getQueryResult(p);
        if(rs==null) return;
        while(rs.next()){
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+
            "\t"+rs.getInt(4)+"\t"+rs.getInt(5)+"\t"+rs.getInt(6));
        }

        rs = cm.getQueryResult(s);
        if(rs==null) return;
        while(rs.next()){
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+
            "\t"+rs.getInt(4));
        }

        rs = cm.getQueryResult(t);
        if(rs==null) return;
        while(rs.next()){
            System.out.println(rs.getInt(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+
            "\t"+rs.getDate(4));
        }

        cm.closeConnection();
    }


}
