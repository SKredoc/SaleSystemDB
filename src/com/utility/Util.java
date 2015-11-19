package com.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wong on 11/19/15.
 */
public class Util {
    public static int getChoice(){
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        int choice = 0;
        while(true){
            try{
                input = stdin.readLine();
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
}
