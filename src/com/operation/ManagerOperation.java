package com.operation;

import com.connection.ConnectionManager;
import com.utility.Util;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wong Kam Shing on 2015/11/20.
 */
public class ManagerOperation {

    public static void showSalesRecord(int sID, String startDate, String endDate){
        ConnectionManager cm;
        ResultSet rs;

        cm = new ConnectionManager();
        String sql = "SELECT T.tID, P.pID, P.pName, M.mName, P.pPrice, T.tDate " +
                "FROM Part P, Transaction T, Manufacturer M " +
                "WHERE P.pID = T.pID AND P.mID = M.mID AND T.sID = "+sID+
                " AND T.tDate >= TO_DATE('"+startDate+"','DD/MM/YYYY') AND " +
                "T.tDate <= TO_DATE('"+endDate+"','DD/MM/YYYY') " +
                "ORDER BY T.tDate DESC";
        rs = cm.getQueryResult(sql);

        if(rs == null){
            return;
        }

        System.out.println("| ID | Part ID | Part Name | Manufacturer | Price | Date |");

        try {
            while(rs.next()){
                System.out.println("| " + rs.getInt(1) + " | " + rs.getInt(2) + " | " + rs.getString(3) + " | " + rs.getString(4) + " | " +
                        rs.getInt(5) + " | " + Util.changeDateFormat(rs.getDate(6).toString()) + " |");
            }
        } catch (SQLException e) {
            System.out.println("Fail to search with errorMsg=["+e.getMessage()+"]");
        }
        cm.closeConnection();

    }

    public static void showTotalSales(){
        ConnectionManager cm;
        ResultSet rs;

        cm = new ConnectionManager();
        String sql = "SELECT M.mID, M.mName, SUM(P.pPrice) AS Total " +
                "FROM Manufacturer M, Part P, Transaction T " +
                "WHERE M.mID = P. mID AND P.pID = T.pID " +
                "GROUP BY M.mID, M.mName " +
                "ORDER BY Total DESC";
        rs = cm.getQueryResult(sql);
        if(rs == null){
            return;
        }
        try{
            while(rs.next()){
                System.out.println("| "+rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getInt(3)+" |");
            }
        } catch(SQLException e){
            System.out.println("Fail to show total sales with errorMsg=["+e.getMessage()+"]");
        }
        cm.closeConnection();
    }

    public static void showPopularPart(int N){
        if(N <= 0)
            return;

        ConnectionManager cm;
        ResultSet rs;

        cm = new ConnectionManager();
        String sql = "SELECT * FROM (SELECT P.pID, P.pName, COUNT(T.tID) AS Total " +
                "FROM Part P, Transaction T " +
                "WHERE P.pID = T.pID " +
                "GROUP BY P.pID , P.pName " +
                "HAVING COUNT(*)>0 " +
                "ORDER BY Total DESC) TEMP WHERE ROWNUM <="+N+"";
        rs = cm.getQueryResult(sql);
        if(rs == null){
            return;
        }
        try{
            while(rs.next()){
                System.out.println("| "+ Util.padLeft(Integer.toString(rs.getInt(1)),9)+ "| " +
                        Util.padLeft(rs.getString(2),30) +"| "+Util.padLeft(Integer.toString(rs.getInt(3)),20) + "|");
            }
        } catch(SQLException e){
            System.out.println("Fail to show total sales with errorMsg=["+e.getMessage()+"]");
        }

    }
}
