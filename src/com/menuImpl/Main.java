package com.menuImpl;

import com.menu.Menu;
import com.utility.Util;

/**
 * Created by wong on 11/19/15.
 */
public class Main implements Menu {
    public static final int administrator = 1;
    public static final int salesPerson = 2;
    public static final int manager = 3;
    public static final int exit = 4;

    @Override
    public void printOperationMenu() {
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
            System.out.println();
            instance.printOperationMenu();
            switch (Util.getChoice()){
                case Main.administrator:
                    Administrator admin = new Administrator();
                    admin.mainOperation(admin);
                    break;
                case Main.salesPerson:
                    SalesPerson salesPerson = new SalesPerson();
                    salesPerson.mainOperation(salesPerson);
                    break;
                case Main.manager:
                    Manager manager = new Manager();
                    manager.mainOperation(manager);
                    break;
                case Main.exit:
                    return;
                default:
                    System.out.println("No this choice. Please input again");
            }
        }
    }
}
