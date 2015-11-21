package com.connection;

import com.constants.Constants;
import com.entity.*;
import com.operation.AdministrationOperation;
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

    public ResultSet getQueryResult(String query){
        try {
            this.stmt = conn.createStatement();
            this.rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Fail to getQueryResult with errorMsg=["+e.getMessage()+"]");
        }
        return rs;
    }

    public void updateQuery(String query){
        try{
            this.stmt = conn.createStatement();
            stmt.executeUpdate(query);
            this.stmt.close();
        } catch (SQLException e) {
            System.out.println("Fail to updateQuery with errorMsg=["+e.getMessage()+"]");
        }
    }

    public int updateQueryList(String[] sqls){
        int f=0;
        try{
            for(String sql : sqls){
                this.stmt = this.conn.createStatement();
                try{
                    stmt.executeUpdate(sql);

                } catch (SQLException e){
                    f=1;
                }
                stmt.close();
            }
        } catch (SQLException e){

        }finally {
            return f;
        }

    }

    public void loadDataFromFile(String folderPath){
        try {
            //load data into Category
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToCategory);
            ArrayList<Category> cList = Util.getCategoryList(Util.getTextFileReader(folderPath, Constants.PATH_category));
            // if(cList == null) System.out.println("Fail to load data into Category");
            if(cList != null){
            for(Category c : cList){
                pstmt.setString(1, Integer.toString(c.getcID()));
                pstmt.setString(2, c.getcName());
                try{
                    pstmt.executeUpdate();
                } catch (SQLException e){
                    System.out.println("Fail to insert data into category with errorMsg=["+e.getMessage()+"]");
                }
            }
            }
            pstmt.close();
            
            //load data into Manufacturer
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToManufacturer);
            ArrayList<Manufacturer> mList = Util.getManufacturerList(Util.getTextFileReader(folderPath, Constants.PATH_manufacturer));
            // if(mList == null) System.out.println("Fail to load data into Manufacturer");
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToManufacturer);
            if(mList!=null){
            for(Manufacturer m : mList){
                pstmt.setString(1, Integer.toString(m.getmID()));
                pstmt.setString(2, m.getmName());
                pstmt.setString(3, m.getmAddress());
                pstmt.setString(4, Integer.toString(m.getmPhoneNumber()));
                pstmt.setString(5, Integer.toString(m.getmWarrantyPeriod()));
                try{
                    pstmt.executeUpdate();
                } catch (SQLException e){
                    System.out.println("Fail to insert data into manufacturer with errorMsg=["+e.getMessage()+"]");
                }
            }
            }
            pstmt.close();

            //load data into Part
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToPart);
            ArrayList<Part> pList = Util.getPartList(Util.getTextFileReader(folderPath, Constants.PATH_part));
            // if(pList == null) System.out.println("Fail to load data into Part");
            if(pList != null){
            for(Part p : pList){
                pstmt.setString(1, Integer.toString(p.getpID()));
                pstmt.setString(2, "'"+p.getpName()+"'");
                pstmt.setString(3, Integer.toString(p.getpPrice()));
                pstmt.setString(4, Integer.toString(p.getmID()));
                pstmt.setString(5, Integer.toString(p.getcID()));
                pstmt.setString(6, Integer.toString(p.getpAvailableQuantity()));
                try{
                    pstmt.executeUpdate();
                } catch (SQLException e){
                    System.out.println("Fail to insert data into part with errorMsg=["+e.getMessage()+"]");
                }
            }
            }
            pstmt.close();

            //load data into SalesPersonMenu
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToSalePerson);
            ArrayList<SalesPerson> sList = Util.getSalesPersonList(Util.getTextFileReader(folderPath, Constants.PATH_salesperson));
            //if(sList == null) System.out.println("Fail to load data into SalesPersonMenu");
            if(sList != null){
            for(SalesPerson s: sList){
                pstmt.setString(1, Integer.toString(s.getsID()));
                pstmt.setString(2, s.getsName());
                pstmt.setString(3, s.getsAddress());
                pstmt.setString(4, Integer.toString(s.getsPhoneNumber()));
                try{
                    pstmt.executeUpdate();
                } catch (SQLException e){
                    System.out.println("Fail to insert data into salesperson with errorMsg=["+e.getMessage()+"]");
                }
            }
            }
            pstmt.close();

            //load data into Transaction
            this.stmt = this.conn.createStatement();
            ArrayList<Transaction> tList = Util.getTransactionList(Util.getTextFileReader(folderPath, Constants.PATH_transaction));
            //if(tList == null) System.out.println("Fail to load data into Transaction");
            if(tList != null){
            for(Transaction t : tList){
                String sql = "INSERT INTO Transaction VALUES ("+t.gettID()+"," +t.getpID()+","+t.getsID()+","+"TO_DATE('"+t.getDate()+"','DD/MM/YYYY')"+")";

                try{
                    stmt.executeUpdate(sql);
                } catch (SQLException e){
                    System.out.println("Fail to insert data into transaction with errorMsg=["+e.getMessage()+"]");
                }
            }
            }
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
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
