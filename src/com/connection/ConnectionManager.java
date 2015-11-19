package com.connection;

import com.constants.Constants;
import com.entity.*;
import com.utility.Util;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Wong Kam Shing on 2015/11/19.
 */
public class ConnectionManager {
    public Connection conn;
    public Statement stmt;
    public ResultSet rs;
    public PreparedStatement pstmt;

    public ConnectionManager() {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch(Exception e){
            System.out.println("Unable to load the driver class");
        }
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@db12.cse.cuhk.edu.hk:1521:db12", Constants.username, Constants.password);
        } catch (SQLException e) {
            System.out.println("Fail to connect to the JDBC");
        }
    }

    public void createAllTable(){
        try {
            this.stmt = this.conn.createStatement();
            for(String sql : Constants.SQLCreateAllTable){
                stmt.executeUpdate(sql);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Fail to create all table");
        }
    }

    public void deleteAllTable(){
        try{
            this.stmt = this.conn.createStatement();
            for(String sql : Constants.SQLDeleteAllTable){
                stmt.executeUpdate(sql);
            }
            stmt.close();
        } catch (SQLException e){
            System.out.println("Fail to delete all table");
        }
    }

    public void loadDataFromFile(String folderPath){
        try {
            //load data into Category
            this.pstmt = this.conn.prepareStatement(Constants.SQLInsertToCategory);
            ArrayList<Category> cList = Util.getCategoryList(Util.getTextFileReader(folderPath, Constants.PATH_category));
            // if(cList == null) System.out.println("Fail to load data into Category");
            for(Category c : cList){
                pstmt.setString(1, Integer.toString(c.getcID()));
                pstmt.setString(2, c.getcName());
            }
            pstmt.close();
            
            //load data into Manufacturer
            this.pstmt = this.conn.prepareStatement(Constants.SQLInsertToManufacturer);
            ArrayList<Manufacturer> mList = Util.getManufacturerList(Util.getTextFileReader(folderPath, Constants.PATH_manufacturer));
            // if(mList == null) System.out.println("Fail to load data into Manufacturer");
            for(Manufacturer m : mList){
                pstmt.setString(1, Integer.toString(m.getmID()));
                pstmt.setString(2, m.getmName());
                pstmt.setString(3, m.getmAddress());
                pstmt.setString(4, Integer.toString(m.getmPhoneNumber()));
                pstmt.setString(5, Integer.toString(m.getmWarrantyPeriod()));
            }
            pstmt.close();
            
            //load data into Part
            this.pstmt = this.conn.prepareStatement(Constants.SQLInsertToPart);
            ArrayList<Part> pList = Util.getPartList(Util.getTextFileReader(folderPath, Constants.PATH_part));
            // if(pList == null) System.out.println("Fail to load data into Part");
            for(Part p : pList){
                pstmt.setString(1, Integer.toString(p.getpID()));
                pstmt.setString(2, p.getpName());
                pstmt.setString(3, Integer.toString(p.getpPrice()));
                pstmt.setString(4, Integer.toString(p.getmID()));
                pstmt.setString(5, Integer.toString(p.getcID()));
                pstmt.setString(6, Integer.toString(p.getpAvailableQuantity()));
            }
            pstmt.close();

            //load data into SalesPerson 
            this.pstmt = this.conn.prepareStatement(Constants.SQLInsertToSalePerson);
            ArrayList<SalesPerson> sList = Util.getSalesPersonList(Util.getTextFileReader(folderPath, Constants.PATH_salesperson));
            //if(sList == null) System.out.println("Fail to load data into SalesPerson");
            for(SalesPerson s: sList){
                pstmt.setString(1, Integer.toString(s.getsID()));
                pstmt.setString(2, s.getsName());
                pstmt.setString(3, s.getsAddress());
                pstmt.setString(4, Integer.toString(s.getsPhoneNumber()));
            }
            pstmt.close();
            
            //load data into Transaction 
            this.pstmt = this.conn.prepareStatement(Constants.SQLInsertToTransaction);
            ArrayList<Transaction> tList = Util.getTransactionList(Util.getTextFileReader(folderPath, Constants.PATH_transaction));
            //if(tList == null) System.out.println("Fail to load data into Transaction");
            for(Transaction t : tList){
                pstmt.setString(1, Integer.toString(t.gettID()));
                pstmt.setString(2, Integer.toString(t.getpID()));
                pstmt.setString(3, Integer.toString(t.getsID()));
                pstmt.setString(4, t.getDate().toString());
            }
            pstmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getQueryResult(String query){
        try {
            this.stmt = conn.createStatement();
            this.rs = stmt.executeQuery(query);
            this.stmt.close();
        } catch (SQLException e) {
            System.out.println("Fail to getQueryResult");
        }
        return rs;
    }

    public void updateQuery(String query){
        try{
            this.stmt = conn.createStatement();
            stmt.executeUpdate(query);
            this.stmt.close();
        } catch (SQLException e) {
            System.out.println("Fail to updateQuery");
        }
    }
    public void closeConnection(){
        try {
            if(this.rs != null) this.rs.close();
            if(this.stmt != null) this.stmt.close();
            if(this.pstmt != null) this.pstmt.close();
            if(this.conn != null) this.conn.close();
        } catch (SQLException e) {
            System.out.println("Fail to close connection");
        }

    }
}
