import com.sun.xml.internal.bind.v2.TODO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wong on 11/19/15.
 */
public class CSCI3170Proj {
    public static final int administrator = 1;
    public static final int salesPerson = 2;
    public static final int manager = 3;
    public static final int exit = 4;

    public static void init(){
        System.out.println("Welcome to sales system!");
        System.out.println("");

    }

    public static void printMainMenu(){
        System.out.println("----Main menu----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Operations for administrator");
        System.out.println("2. Operations for salesperson");
        System.out.println("3. Operations for manager");
        System.out.println("4. Exit this program");
    }

    public static int getChoice(BufferedReader br){
        System.out.println("Enter Your Choice: ");
        String input = null;
        int choice = 0;
        while(true){
            try{
                input = br.readLine();

            } catch (IOException e) {
                System.out.println("CSCI3170Proj.getChoice: IOException");
            }
            try{
                choice = Integer.parseInt(input);
                return choice;
            } catch (NumberFormatException nfe){
                System.out.println("It is not a valid number. Please enter again: ");
            }
        }
    }

    public static void administratorOperation(){

    }

    public static void salesPersonOperation(){

    }

    public static void managerOperation(){

    }

    public static void main(String args[]){
        init();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //TODO connect to jdbc;
        while(true){
            printMainMenu();
            switch (getChoice(input)){
                case administrator:
                    administratorOperation();
                    break;
                case salesPerson:
                    salesPersonOperation();
                    break;
                case manager:
                    managerOperation();
                    break;
                case exit:
                    return;
            }
        }
    }

}
