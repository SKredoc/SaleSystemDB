import com.menuImpl.MainMenu;

/**
 * Created by wong on 11/19/15.
 */

public class CSCI3170Proj {
    public static void init(){
        System.out.println("Welcome to sales system!");
    }

    public static void main(String args[]){
        init();
        //TODO connect to jdbc;
        MainMenu instance = new MainMenu();
        instance.mainOperation(instance);
    }

}
