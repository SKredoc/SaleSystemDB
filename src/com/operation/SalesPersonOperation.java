package com.operation;

import com.connection.ConnectionManager;
import com.utility.Util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wong Kam Shing on 2015/11/20.
 */
public class SalesPersonOperation {
    public static void sellAPart(int pID, int sID){
        ConnectionManager cm = null;
        ResultSet rs = null;

        cm = new ConnectionManager();
        rs = cm.getQueryResult("SELECT P.pName, P.pAvailableQuantity FROM Part P WHERE P.pID = " + pID);
        try {
            rs.next();
            String pName = rs.getString("pName");
            int pQuantity = rs.getInt("pAvailableQuantity");
            rs.close();
            if(pQuantity > 0){
                System.out.println("Product: " + pName + "(id: " + pID + ") Remaining Quality: " + (pQuantity-1));
                int maxtID = 0; //TODO is the tID increase in ascending order?
                rs = cm.getQueryResult("SELECT MAX(T.tID) FROM Transaction T");
                rs.next();
                maxtID =  rs.getInt(1);
                rs.close();
                System.out.println("The maximum of tid is " + maxtID); //TODO delete
                cm.updateQuery("INSERT INTO Transaction VALUES (" + (maxtID+1) + "," + pID + "," + sID + "," + Util.getCurrentDate() + ")");
            }
            else{
                System.out.println("Error: Not enough Quality of Product: " + pName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cm.closeConnection();
    }

    public static void search(String pattern, String order){
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

}
