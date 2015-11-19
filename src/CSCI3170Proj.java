import com.menu.Administrator;
import com.menu.Main;
import com.sun.xml.internal.bind.v2.TODO;
import com.utility.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * Created by wong on 11/19/15.
 */
public class CSCI3170Proj {
    public static void init(){
        System.out.println("Welcome to sales system!");
        System.out.println("");
    }

    public static void main(String args[]){
        init();
        //TODO connect to jdbc;
        Main instance = new Main();
        instance.mainOperation(instance);
    }

}
