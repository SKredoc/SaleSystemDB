package com.operation;

import com.connection.ConnectionManager;
import com.constants.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wong Kam Shing on 2015/11/20.
 */
public class AdministrationOperation {
    public static String[] SQLCreateAllTable = new String[]{
            "CREATE TABLE Category (" +
                    "cID NUMBER(1) PRIMARY KEY, " +
                    "cName VARCHAR(20)"+
                    ")",
            "CREATE TABLE Manufacturer (" +
                    "mID NUMBER(2) PRIMARY KEY, " +
                    "mName VARCHAR(20), " +
                    "mAddress VARCHAR(50), " +
                    "mPhoneNumber NUMBER(8), " +
                    "mWarrantyPeriod NUMBER(1), " +
                    "CONSTRAINT CheckManufacturerPhoneNumber CHECK (mPhoneNumber > 9999999)" +
                    ")",
            "CREATE TABLE Part (" +
                    "pID NUMBER(3) PRIMARY KEY, " +
                    "pName VARCHAR(20), " +
                    "pPrice NUMBER(5), " +
                    "mID NUMBER(2), " +
                    "cID NUMBER(1), " +
                    "pAvailableQuantity NUMBER(2), " +
                    "FOREIGN KEY (mID) REFERENCES Manufacturer (mID) ON DELETE CASCADE, " +
                    "FOREIGN KEY (cID) REFERENCES Category (cID) ON DELETE CASCADE" +
                    ")",
            "CREATE TABLE SalesPerson (" +
                    "sID NUMBER(2) PRIMARY KEY, " +
                    "sName VARCHAR(20), " +
                    "sAddress VARCHAR(50), " +
                    "sPhoneNumber NUMBER(8), " +
                    "CONSTRAINT CheckSalesPersonPhoneNumber CHECK (sPhoneNumber > 9999999)" +
                    ")",
            "CREATE TABLE Transaction (" +
                    "tID NUMBER(4) PRIMARY KEY, " +
                    "pID NUMBER(3), " +
                    "sID NUMBER(2), " +
                    "tDate DATE, " +
                    "FOREIGN KEY (pID) REFERENCES Part (pID) ON DELETE CASCADE, " +
                    "FOREIGN KEY (sID) REFERENCES SalesPerson (sID) ON DELETE CASCADE " +
                    ")"
    };

    public static String[] SQLDeleteAllTable = new String[]{
            "DROP TABLE Transaction",
            "DROP TABLE Part",
            "DROP TABLE Category",
            "DROP TABLE Manufacturer",
            "DROP TABLE SalesPerson",
    };

    public static String SQLInsertToCategory = "INSERT INTO Category VALUES (?,?)";
    public static String SQLInsertToManufacturer = "INSERT INTO Manufacturer VALUES (?,?,?,?,?)";
    public static String SQLInsertToPart = "INSERT INTO Part VALUES (?,?,?,?,?,?)";
    public static String SQLInsertToSalePerson = "INSERT INTO SalesPerson VALUES (?,?,?,?)";
    public static String SQLInsertToTransaction = "INSERT INTO Transaction VALUES (?,?,?,?)";

    public static int createAllTable(){
        ConnectionManager cm = new ConnectionManager();
        int f = cm.updateQueryList(SQLCreateAllTable);
        cm.closeConnection();
        return f;
    }

    public static int deleteAllTable(){
        ConnectionManager cm = new ConnectionManager();
        int f = cm.updateQueryList(SQLDeleteAllTable);
        cm.closeConnection();
        return f;
    }

    public static void loadDataFromFile(String folderPath){
        ConnectionManager cm = new ConnectionManager();
        cm.loadDataFromFile(folderPath);
        cm.closeConnection();
    }

    public static HashMap<String,Integer> showNumberOfRecord(){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        ResultSet rs = null;
        ConnectionManager cm = new ConnectionManager();

        try {
            rs = cm.getQueryResult("SELECT COUNT(*) FROM Category");
            if(rs == null) return map;
            rs.next();
            map.put(Constants.TABLE_Category, rs.getInt(1));
            rs.close();

            rs = cm.getQueryResult("SELECT COUNT(*) FROM Manufacturer");
            if(rs == null) return map;
            rs.next();
            map.put(Constants.TABLE_Manufacturer, rs.getInt(1));
            rs.close();

            rs = cm.getQueryResult("SELECT COUNT(*) FROM Part");
            if(rs == null) return map;
            rs.next();
            map.put(Constants.TABLE_Part, rs.getInt(1));
            rs.close();

            rs = cm.getQueryResult("SELECT COUNT(*) FROM SalesPerson");
            if(rs == null) return map;
            rs.next();
            map.put(Constants.TABLE_SalesPerson, rs.getInt(1));
            rs.close();

            rs = cm.getQueryResult("SELECT COUNT(*) FROM Transaction");
            if(rs == null) return map;
            rs.next();
            map.put(Constants.TABLE_Transaction, rs.getInt(1));
            rs.close();
        } catch (SQLException e) {
            System.out.println("Fail to count the number of each table with errorMsg=["+e.getMessage()+"]");
        }

        cm.closeConnection();
        return map;
    }
}