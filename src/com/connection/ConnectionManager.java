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
        System.out.println("ConnectionManager.Constructor#start");
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
        System.out.println("ConnectionManager.Constructer#end - connection is established!");
    }

    public ResultSet getQueryResult(String query){
        System.out.println("ConnectionManager.getQueryResult#start query=[" + query +"]");
        try {
            this.stmt = conn.createStatement();
            this.rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Fail to getQueryResult with errorMsg=["+e.getMessage()+"]");
        }
        System.out.println("ConnectionManager.getQueryResult#end finished query");
        return rs;
    }

    public void updateQuery(String query){
        System.out.println("ConnectionManager.updateQuery#start - query=["+query+"]");
        try{
            this.stmt = conn.createStatement();
            stmt.executeUpdate(query);
            this.stmt.close();
        } catch (SQLException e) {
            System.out.println("Fail to updateQuery with errorMsg=["+e.getMessage()+"]");
        }
        System.out.println("ConnectionManager.updateQuery#end - finished updating query");
    }

    public void updateQueryList(String[] sqls){
        System.out.println("ConnectionManager.updateQueryList#start - sql list size = " + sqls.length);
        try{
            for(String sql : sqls){
                this.stmt = this.conn.createStatement();
                try{
                    stmt.executeUpdate(sql);

                } catch (SQLException e){
                    System.out.println(e.getMessage());
                    System.out.println("Fail to update the sql=[" + sql+"], errorMsg=["+e.getMessage()+"]");
                }
                stmt.close();
            }
        } catch (SQLException e){
            System.out.println("Fail to update query list");
        }
        System.out.println("ConnectionManager.updateQueryList#end finished updating query list");

    }

    public void loadDataFromFile(String folderPath){
        System.out.println("ConnectionManager.loadDataFromFile#start - folderPath=["+folderPath+"]");
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
                    System.out.println("Fail to update the sql=["+AdministrationOperation.SQLInsertToCategory+"] with cID=["+c.getcID()+"], cName=["+c.getcName()+"], errorMsg=["+e.getMessage()+"]");
                }
            }
            }
            pstmt.close();
            
            //load data into Manufacturer
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToManufacturer);
            ArrayList<Manufacturer> mList = Util.getManufacturerList(Util.getTextFileReader(folderPath, Constants.PATH_manufacturer));
            // if(mList == null) System.out.println("Fail to load data into Manufacturer");
            if(mList != null){

            }
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToManufacturer);
            for(Manufacturer m : mList){
                pstmt.setString(1, Integer.toString(m.getmID()));
                pstmt.setString(2, m.getmName());
                pstmt.setString(3, m.getmAddress());
                pstmt.setString(4, Integer.toString(m.getmPhoneNumber()));
                pstmt.setString(5, Integer.toString(m.getmWarrantyPeriod()));
                try{
                    pstmt.executeUpdate();
                } catch (SQLException e){
                    System.out.println("Fail to update the sql=["+AdministrationOperation.SQLInsertToManufacturer+"] with mID=["+m.getmID()+"], mName=["+m.getmName()+"], " +
                            "mAddress=["+m.getmAddress()+"], mPhoneNumber=["+m.getmPhoneNumber()+"], mWarrantyPeriod=["+m.getmWarrantyPeriod()+"], errorMsg=["+e.getMessage()+"]");
                }
            }
            pstmt.close();

            //load data into Part
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToPart);
            ArrayList<Part> pList = Util.getPartList(Util.getTextFileReader(folderPath, Constants.PATH_part));
            // if(pList == null) System.out.println("Fail to load data into Part");
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
                    System.out.println("Fail to update sql=["+AdministrationOperation.SQLInsertToPart+"], pID=[" + p.getpID()+"], pName=["+p.getpName()+"], pPrice=["+p.getpPrice()+"], mID=["+p.getmID()+"], cID=[" + p.getcID() +"]" +
                            ", pAvailableQuantity=["+p.getpAvailableQuantity()+"], errorMsg=["+e.getMessage()+"]");
                }
            }
            pstmt.close();

            //load data into SalesPersonMenu
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToSalePerson);
            ArrayList<SalesPerson> sList = Util.getSalesPersonList(Util.getTextFileReader(folderPath, Constants.PATH_salesperson));
            //if(sList == null) System.out.println("Fail to load data into SalesPersonMenu");
            for(SalesPerson s: sList){
                pstmt.setString(1, Integer.toString(s.getsID()));
                pstmt.setString(2, s.getsName());
                pstmt.setString(3, s.getsAddress());
                pstmt.setString(4, Integer.toString(s.getsPhoneNumber()));
                try{
                    pstmt.executeUpdate();
                } catch (SQLException e){
                    System.out.println("Fail to update sql=["+AdministrationOperation.SQLInsertToSalePerson+"], sID=["+s.getsID()+"], sName=["+s.getsName()+"], sAddress=["+s.getsAddress()+"], sPhoneNumber=["+s.getsPhoneNumber()+"]," +
                            " ErrorMsg=["+e.getMessage()+"]");
                }
            }
            pstmt.close();
            
            //load data into Transaction 
            this.pstmt = this.conn.prepareStatement(AdministrationOperation.SQLInsertToTransaction);
            ArrayList<Transaction> tList = Util.getTransactionList(Util.getTextFileReader(folderPath, Constants.PATH_transaction));
            //if(tList == null) System.out.println("Fail to load data into Transaction");
            for(Transaction t : tList){
                pstmt.setString(1, Integer.toString(t.gettID()));
                pstmt.setString(2, Integer.toString(t.getpID()));
                pstmt.setString(3, Integer.toString(t.getsID()));
                pstmt.setDate(4, t.getDate());
                try{
                    pstmt.executeUpdate();
                } catch (SQLException e){
                    System.out.println("Fail to update sql=["+AdministrationOperation.SQLInsertToTransaction+"], tID=["+t.gettID()+"], pID=["+t.getpID()+"], sID=["+t.getsID()+"], date=["+t.getDate()+"], errorMsg=["+e.getMessage()+"]");
                }
            }
            pstmt.close();
            System.out.println("ConnectionManager.loadDataFromFile# date string = " + tList.get(0).getDate().toString());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("ConnectionManager.loadDataFromFile#end finished loadDataFromFile");
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
