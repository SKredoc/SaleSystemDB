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
        System.out.println("SalesPersonOperation.sellAPart#start - pID=["+pID+"], sID=["+sID+"]");
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
                cm.updateQuery("INSERT INTO Transaction VALUES (" + (maxtID+1) + "," + pID + "," + sID + "," + "TO_DATE('"+Util.getCurrentDate()+"', 'DD/MM/YYYY')" + ")");
                cm.updateQuery("UPDATE Part SET pAvailableQuantity="+(pQuantity-1)+" WHERE pID="+pID);
            }
            else{
                System.out.println("Error: Not enough Quality of Product: " + pName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cm.closeConnection();
        System.out.println("SalesPersonOperation.sellAPart#end");
    }

    public static void search(String pattern, String order){
        System.out.println("SalesPersonOperation.search#start - pattern=["+pattern+"] order=["+order+"]");
        ConnectionManager cm;
        ResultSet rs;

        cm = new ConnectionManager();
        rs = cm.getQueryResult("SELECT P.pID, P.pName, M.mName, C.cName, P.pAvailableQuantity, M.mWarrantyPeriod, P.pPrice " +
                "FROM Part P, Manufacturer M, Category C " +
                "WHERE P.mID = M.mID AND P.cid = C.cid AND " + pattern +
                " ORDER BY P.pPrice " + order);

        if(rs == null){
            System.out.println("No matched result is found");
            return;
        }

        System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
        try {
            while(rs.next()){
                System.out.println("| " + rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " +
                        rs.getInt(5) + " | " + rs.getInt(6) + " | " + rs.getInt(7) + " |");
            }
        } catch (SQLException e) {
            System.out.println("Fail to search with errorMsg=["+e.getMessage()+"]");
        }
        cm.closeConnection();
        System.out.println("End of Query");
        System.out.println("SalesPersonOperation.search#end");
    }

}
