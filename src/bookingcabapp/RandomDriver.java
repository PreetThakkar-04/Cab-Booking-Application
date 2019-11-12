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
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomDriver extends Thread {
    static public int flag;
    public void run(){
        flag = 1;
        while(flag == 1){
        randomDriverAllocate();
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RandomDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    void randomDriverAllocate(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabbookingapp?serverTimezone=UTC","root","preet@0431");
            String query = "select Aval,drivername from driver";
            ResultSet rs;
            PreparedStatement st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
               int result = rs.getInt(1);
               String name = rs.getString(2);
               if( result == 1 ){
                   int x = (int)(Math.random()*1000);
                   String query1 = "update cabbookingapp.driver set location = ? where drivername = ?";
                   PreparedStatement st1 = con.prepareStatement(query1);
                   st1.setString(2,name);
                   st1.setInt(1, x%5);
                   st1.executeUpdate();
               }
            }
            st.close();
            con.close();
            }
            catch(Exception e){
            System.out.println("Exception1:"+e);
            }
    }
    void exitRandomDriver(){
        System.exit(0);
    }
}
