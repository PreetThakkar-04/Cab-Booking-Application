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


public class Customer {
        static private String customerName,driver,customerEmail,customerPasswd,customerPhone;
        static private int customerWallet;
        public Customer(String customerName,String customerEmail,String customerPasswd,String customerPhone, int customerWallet)
        {
            this.customerName = customerName;
            this.driver="";
            this.customerEmail=customerEmail;
            this.customerWallet=customerWallet;
            this.customerPasswd=customerPasswd;
            this.customerPhone=customerPhone;
        }
        static public int BookACab(int pi){
            int locationArr[]=new int[3];
            int ratingArr[]=new int [3];
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabbookingapp?serverTimezone=UTC","root","preet@0431");
            ResultSet rs;
            String query = "select rating,location from driver";
            PreparedStatement st = con.prepareStatement(query);
            rs = st.executeQuery();
            int i=0;
            while(rs.next()){
                locationArr[i]=rs.getInt("location");
                ratingArr[i]=rs.getInt("rating");
                ++i;
            }
            st.close();
            con.close();
            }
            catch(Exception e){
            System.out.println("Exception:"+e);
            }   
            Location lo =new Location();
            int minDist=100,maxRating=0,driverIndex=0;
            for(int j=0;j<3;++j){
                if(lo.getDistance(pi,locationArr[j])<minDist){
                        minDist=lo.getDistance(pi,locationArr[j]);
                        maxRating=ratingArr[j];
                        driverIndex=j;
                        continue;
                }
                else if(lo.getDistance(pi,locationArr[j])==minDist  && ratingArr[j]>maxRating){
                         maxRating=ratingArr[j];
                         driverIndex=j;
                }
            }
            return driverIndex;
        }
        public static int getWallet(){
            return customerWallet;
        }
        public static void reduceWallet(int fare){
            customerWallet = customerWallet - fare;
            System.out.println(customerWallet);
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabbookingapp?serverTimezone=UTC","root","preet@0431");
            String query = "update cabbookingapp.userdetails set wallet = ? where username = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, customerWallet);
            st.setString(2, customerName);
            int count = st.executeUpdate();
            st.close();
            con.close();
            }
            catch(Exception e){
            System.out.println("Exception:"+e);
            }
        }
}
