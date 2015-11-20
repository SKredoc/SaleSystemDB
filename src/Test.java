import com.connection.ConnectionManager;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Wong Kam Shing on 2015/11/19.
 */
public class Test {
    public static void main(String[] args){
        ConnectionManager cm = new ConnectionManager();
        String sql = "INSERT INTO Manufacturer VALUES (1,Intel,'abcdefghijklmnopqrstuvwyxzabcdefghijklmnopqrstuvw',39438910,5)";
        cm.updateQuery(sql);
        cm.closeConnection();


    }
}
