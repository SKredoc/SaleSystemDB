package com.menuImpl;

import com.menu.Menu;
import com.utility.Util;

/**
 * Created by wong on 11/19/15.
 */
public class MainMenu implements Menu {
    public static final int administrator = 1;
    public static final int salesPerson = 2;
    public static final int manager = 3;
    public static final int exit = 4;

    @Override
    public void printOperationMenu() {
        System.out.println();
        System.out.println("-----MainMenu menu-----");
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
                default:
                    System.out.println("No this choice. Please input again");
            }
        }
    }
}
