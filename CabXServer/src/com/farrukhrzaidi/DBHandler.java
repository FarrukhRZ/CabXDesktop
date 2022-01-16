package com.farrukhrzaidi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBHandler {
    private Connection con;
    public void CreateConnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/farrukhrz", "CabXManager", "1234");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public int updateTable(String query){
        try{
            Statement stmt=con.createStatement();
            int res=stmt.executeUpdate(query);
            return  res;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public ArrayList<Passenger> getPassengers(){
        ArrayList<Passenger> passengerArrayList=new ArrayList<>();
        try{
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet ps=stmt.executeQuery("select p.u_id,p.u_name,p.password,p.rating,p.contact,p.email,p.ride_count,p.credit,p.name from Passenger p order by u_id");
            while(ps.next()) {
                passengerArrayList.add(new Passenger(ps.getInt(1),ps.getString(2),ps.getString(3),ps.getFloat(4),ps.getLong(5),ps.getString(6),ps.getInt(7), ps.getInt(8),ps.getString(9),123456789));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return passengerArrayList;
    }
    public ArrayList<Driver> getDrivers(){
        ArrayList<Driver> driverArrayList=new ArrayList<>();
        try{
            Statement stmt=con.createStatement();
            ResultSet ds=stmt.executeQuery("select d.d_id,d.u_name,d.password," +
                    "d.rating,d.contact,d.email,d.ride_count,d.reg_date,d.name,d.cnic," +
                    "c.name,c.model,c.registration# from driver d,car c where d.d_ID=c.D_ID");
            while(ds.next())
                driverArrayList.add(new Driver(ds.getInt(1),ds.getString(2),ds.getString(3),
                        ds.getFloat(4),ds.getLong(5),ds.getString(6),ds.getInt(7),
                        ds.getDate(8),ds.getString(9),ds.getLong(10),new Car(ds.getString(11),ds.getInt(12),ds.getInt(13))));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return driverArrayList;
    }
    public  ArrayList<Ride> getRides(){
        ArrayList<Ride> rideArrayList=new ArrayList<>();
        try{
            Statement stmt=con.createStatement();
            ResultSet rides=stmt.executeQuery("select r.*,f.base,f.peak,f.total from ridehistory r ,fare f where r.Ride_ID=f.Ride_ID");
            while(rides.next())
                rideArrayList.add(new Ride(rides.getInt(1),rides.getInt(2),rides.getInt(3),rides.getDate(4),rides.getFloat(5),
                        rides.getString(6),rides.getString(7),new Fare(rides.getFloat(8),rides.getFloat(9),rides.getFloat(10)),0.0f,0.0f));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return rideArrayList;
    }

}
