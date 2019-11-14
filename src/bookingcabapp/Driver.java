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
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


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
    public static void removeDriver(String dname)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabbookingapp?serverTimezone=UTC","root","preet@0431");
            ResultSet rs;
            String query2 = "delete from driver where drivername = ?";
            PreparedStatement st = con.prepareStatement(query2);
            st.setString(1, dname);
            int count = st.executeUpdate();
            st.close();
            con.close();
            }
            catch(Exception e){
            System.out.println("Exception:"+e);
            }
    }
    public static void addDriver(String drivname, String drivPhno, String drivTno, int intRat )
    {   
        int flag = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabbookingapp?serverTimezone=UTC","root","preet@0431");
            PreparedStatement st = con.prepareStatement("insert into driver values(?,?,?,?,?,1)");
            PreparedStatement st1 = con.prepareStatement("select phno,tno from driver");
            ResultSet rs;
            rs = st1.executeQuery();
            while(rs.next()){
                String phonever = rs.getString("phno");
                String tnover = rs.getString("tno");
                if( drivPhno.equals(phonever) ){
                    flag = 1;
                }
                if( drivTno.equals(tnover) ){
                    flag = 2;
                }
            }
            int x = (int)(Math.random()*1000);
            st.setString(1, drivname);
            st.setString(2, drivPhno);
            st.setString(3, drivTno);
            st.setDouble(4,intRat);
            st.setInt(5, x%5);
            if( flag == 1){
                JOptionPane.showMessageDialog(null, "Phone number already exists!");
            }
            else if( flag == 2){
                JOptionPane.showMessageDialog(null, "Taxi number already exists!");
            }
            else if (drivname.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Add Drivername");
            }
            else if( drivPhno.equals("") ){
                JOptionPane.showMessageDialog(null, "Enter Driver Phone Number");
            }
            else if( drivTno.equals("") ){
                JOptionPane.showMessageDialog(null, "Enter Taxi Number");
            }
            else{
                if( st.executeUpdate() > 0){
                 JOptionPane.showMessageDialog(null, "New Driver Added Succesfully!");
                }
            }    
            st.close();
            con.close();
        }catch(SQLIntegrityConstraintViolationException ex){ 
           JOptionPane.showMessageDialog(null, "Driver name already exists!");
        }
        catch(Exception e){
            System.out.println("Exec:"+e);
        }
    }
}
