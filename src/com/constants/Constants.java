package com.constants;

/**
 * Created by Wong Kam Shing on 2015/11/19.
 */
public class Constants {
    public static String username = "d072";
    public static String password = "hsupdehh";

    public static String PATH_category = "\\category.txt";
    public static String PATH_manufacturer = "\\manufacturer.txt";
    public static String PATH_part = "\\part.txt";
    public static String PATH_salesperson = "\\salesperson.txt";
    public static String PATH_transaction = "\\transaction.txt";

    public static String TABLE_Category = "Category";
    public static String TABLE_Manufacturer = "Manufacturer";
    public static String TABLE_Part = "Part";
    public static String TABLE_SalesPerson = "SalesPerson";
    public static String TABLE_Transaction = "Transaction";

    public static String[] SQLCreateAllTable = new String[]{
            "CREATE TABLE Category (" +
                    "cID NUMBER(1) PRIMARY KEY " +
                    "cName VARCHAR(20)"+
                    ")",
            "CREATE TABLE Manufacturer (" +
                    "mID NUMBER(2) PRIMARY KEY " +
                    "mName VARCHAR(20) " +
                    "mAddress VARCHAR(50) " +
                    "mPhoneNumber NUMBER(8) " +
                    "mWarrantyPeriod NUMBER(1) " +
                    "CONSTRAINT CheckPhoneNumber CHECK (mPhoneNumber > 9999999)" +
                    ")",
            "CREATE TABLE Part (" +
                    "pID NUMBER(3) PRIMARY KEY " +
                    "pName VARCHAR(20) " +
                    "pPrice NUMBER(5) " +
                    "mID NUMBER(2) " +
                    "cID NUMBER(1) " +
                    "pAvailableQuantity NUMBER(2) " +
                    "FOREIGN KEY (mID) REFERENCES Manufacturer (mID) ON DELETE CASCADE " +
                    "FOREIGN KEY (cID) REFERENCES Category (cID) ON DELETE CASCADE" +
                    ")",
            "CREATE TABLE SalesPerson (" +
                    "sID NUMBER(2) PRIMARY KEY " +
                    "sName VARCHAR(20) " +
                    "sAddress VARCHAR(50) " +
                    "sPhoneNumber NUMBER(8) " +
                    "CONSTRAINT CheckPhoneNumber CHECK (sPhoneNumber > 9999999)" +
                    ")",
            "CREATE TABLE Transaction (" +
                    "tID NUMBER(4) PRIMARY KEY " +
                    "pID NUMBER(3) " +
                    "sID NUMBER(2) " +
                    "tDate DATE " +
                    "FOREIGN KEY (pID) REFERENCES Part (pID) ON DELETE CASCADE " +
                    "FOREIGN KEY (sID) REFERENCES SalePerson (sID) ON DELETE CASCADE" +
                    ")"
    };

    public static String[] SQLDeleteAllTable = new String[]{
            "DELETE FROM Category",
            "DELETE FROM Manufacturer",
            "DELETE FROM Part",
            "DELETE FROM SalePerson",
            "DELETE FROM Transaction"
    };

    public static String SQLInsertToCategory = "INSERT INTO Category VALUES (?,?)";
    public static String SQLInsertToManufacturer = "INSET INTO Manufacturer VALUES (?,?,?,?,?)";
    public static String SQLInsertToPart = "INSERT INTO Part VALUES (?,?,?,?,?,?)";
    public static String SQLInsertToSalePerson = "INSERT INTO SalePerson (?,?,?,?)";
    public static String SQLInsertToTransaction = "INSERT INTO Transaction (?,?,?,?)";
}
