/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingcabapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Driver {
    public static void updateAval(int driverIndex, int aval)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabbookingapp?serverTimezone=UTC","root","preet@0431");
            ResultSet rs;
            String query1 = "select * from driver";
            PreparedStatement st1 = con.prepareStatement(query1);
            rs = st1.executeQuery();
                rs.next();
            for(int i=0;i<driverIndex;++i)
                    rs.next();
            String name = rs.getString("drivername");
            String query2 = "update cabbookingapp.driver set Aval = ? where drivername = ?";
            PreparedStatement st2 = con.prepareStatement(query2);
            st2.setInt(1, aval);
            st2.setString(2, name);
            int count = st2.executeUpdate();
            st2.close();
            st1.close();
            con.close();
            }
            catch(Exception e){
            System.out.println("Exception:"+e);
            }
    }
    public static void updateRating(int driverIndex,int rat)
    {
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabbookingapp?serverTimezone=UTC","root","preet@0431");
            ResultSet rs;
            String query1 = "select * from driver";
            PreparedStatement st1 = con.prepareStatement(query1);
            rs = st1.executeQuery();
                rs.next();
            for(int i=0;i<driverIndex;++i)
                    rs.next();
            String name = rs.getString("drivername");
            double prevRating = rs.getDouble("rating");
            double nextRating = (prevRating + rat)/2;
            String query2 = "update cabbookingapp.driver set rating = ? where drivername = ?";
            PreparedStatement st2 = con.prepareStatement(query2);
            st2.setDouble(1, nextRating);
            st2.setString(2, name);
            int count = st2.executeUpdate();
            st2.close();
            st1.close();
            con.close();
            }
            catch(Exception e){
            System.out.println("Exception:"+e);
            }
    }
}
