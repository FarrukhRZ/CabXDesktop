package com.farrukhrzaidi;
import java.net.Socket;
import java.sql.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static ArrayList<Socket> onlineDrivers;
    public static ArrayList<BookRide> bookRideArrayList;
    public static void main(String[] args) {
        DBHandler dbHandler=new DBHandler();
        dbHandler.CreateConnection();
        bookRideArrayList=new ArrayList<>();
        onlineDrivers=new ArrayList<>();
        ArrayList<Driver> driver_list = dbHandler.getDrivers();
        ArrayList<Passenger> passenger_list = dbHandler.getPassengers();
        ArrayList<Ride> rideArrayList = dbHandler.getRides();
        for(Passenger p1:passenger_list){
            p1.display();
        }
        for(Driver d1:driver_list){
            System.out.println(d1.getName()+"  "+d1.getuName()+"  "+d1.getPassword()+"  "+d1.getEmail()+"  "+d1.getContactNo()+"  "+
                    d1.getDriverID()+d1.getReg_date()+d1.getRating()+d1.getRideCount()+"  "+d1.getCar().getName()+"  "+d1.getCar().getModel()+d1.getCar().getRegistration());
        }
        for(Ride r1:rideArrayList){
            System.out.println(r1.getPickup()+" "+r1.getDropOff()+" "+r1.getDistance()+" "+r1.getFare().getTotalFare()+" "+r1.getRideDate());
        }
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            Talker talker;
            while(true) {
                new Talker(serverSocket.accept(),passenger_list,driver_list,rideArrayList,dbHandler).start();
                System.out.println("Client Connected");
            }

        } catch(IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
    public static ArrayList<Socket> getOnlineDrivers() {
        return onlineDrivers;
    }

    public static void setOnlineDrivers(ArrayList<Socket> onlineDrivers) {
        Main.onlineDrivers = onlineDrivers;
    }
    public static void addDriver(Socket s1){
        onlineDrivers.add(s1);
    }
    public static void deleteDriver(Socket s1){
        onlineDrivers.remove(s1);
    }

}
