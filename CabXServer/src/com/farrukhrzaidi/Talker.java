package com.farrukhrzaidi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Talker extends Thread {
    private boolean type;
    private int index;
    private Socket socket;
    private ArrayList<Passenger> passengers;
    private ArrayList<Driver> drivers;
    private ArrayList<Ride> rideArrayList;
    private String uname;
    private int u_id;
    private DBHandler dbHandler;

    public Talker(Socket socket, ArrayList<Passenger> passengers, ArrayList<Driver> drivers, ArrayList<Ride> ride,DBHandler dbHandler) {
        this.socket = socket;
        this.passengers = passengers;
        this.drivers = drivers;
        this.rideArrayList = ride;
        this.dbHandler=dbHandler;
        index=-1;
    }
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public ArrayList<Ride> getRide() {
        return rideArrayList;
    }

    public void setRide(ArrayList<Ride> ride) {
        rideArrayList = ride;
    }
    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            String cType=input.readLine();
            if(cType.equals("Passenger")){
                type=true;
                output.println("connected");
            }
            else{
                type=false;
                output.println("connected");
            }
            if(type){
                while(true){
                    String choice=input.readLine();
                    if(choice.equals("1")){
                        String buffer=input.readLine();
                        StringTokenizer s1=new StringTokenizer(buffer);
                        uname=s1.nextToken();
                        String password=s1.nextToken();
                        boolean notfound=true;
                        System.out.println(uname+" "+password);
                        for(Passenger p1:passengers){
                            if(p1.getUserName().equals(uname)&&p1.getPassword().equals(password)){
                                notfound=false;
                                u_id=p1.getPassengerID();
                                output.println("successful");
                                System.out.println("success");
                            }
                        }
                        if(notfound)
                            output.println("failed");
                    }
                    else if(choice.equals("2")){
                        String msg;
                        int pid=0;
                        for(Passenger p1:passengers){
                            if(p1.getUserName().equals(uname)){
                                msg=("Name:"+p1.getName()+" "+"Contact:"+p1.getContactNo()+" "+"Email:"+p1.getEmail()+" "+
                                        "Credit:"+p1.getCredit()+" "+"Rating:"+p1.getRating());
                                output.println(msg);
                                pid=p1.getPassengerID();
                                System.out.println(msg);
                            }
                        }
                        ArrayList<Ride> r2=new ArrayList<>();
                        for(Ride r1:rideArrayList){
                            if(r1.getPassengerID()==pid){
                                r2.add(r1);
                            }
                        }
                        for(Ride r1:r2){
                            System.out.println(r1.Diplayride());
                            output.println(r1.Diplayride());
                        }
                        output.println("exit");

                    }
                    else if(choice.equals("3")){
                        String msg=input.readLine();
                        StringTokenizer st=new StringTokenizer(msg);
                        String msg2=input.readLine();
                        StringTokenizer st2=new StringTokenizer(msg2);
                        int count=passengers.size();
                        count++;
                        uname=st2.nextToken();
                        for(Passenger p1:passengers){
                            if(p1.getUserName().equals(uname)){
                                output.println("no");
                                break;
                            }
                        }
                        dbHandler.updateTable("insert into passenger(Name,Email,Contact,u_name,password,rating,ride_count,credit,cnic,u_ID) values('"+st.nextToken()+"','"+st.nextToken()
                                +"',"+st.nextToken()+",'"+uname+"','"+st2.nextToken()+"',5,0,0,0000,"+count+")");
                        output.println("yes");
                    }
                    else if(choice.equals("4")){
                        String msg=input.readLine();
                        StringTokenizer st=new StringTokenizer(msg);
                        System.out.println(msg);
                        Main.bookRideArrayList.add(new BookRide(getUserDetails(),st.nextToken(),st.nextToken(),u_id));
                        index=Main.bookRideArrayList.size()-1;
                        while(!Main.bookRideArrayList.get(index).isAccepted()){

                        }
                        System.out.println("driver found");
                        output.println(Main.bookRideArrayList.get(index).getDriverDetails());
                        System.out.println(Main.bookRideArrayList.get(index).getDriverDetails());
                    }
                    else if(choice.equals("5")){
                        if(Main.bookRideArrayList.get(index).isStarted()){
                            output.println("no");
                        }
                        else if(Main.bookRideArrayList.get(index).isStarted()){
                            output.println("comp");
                        }
                        else{
                            output.println("yes");
                            Main.bookRideArrayList.get(index).setCancelled(true);
                        }
                    }
                    else if(choice.equals("6")){
                        System.out.println("45");
                        String buffer=input.readLine();
                        StringTokenizer details=new StringTokenizer(buffer);
                        System.out.println("hello");
                        String query="update passenger set password= '"+details.nextToken()+
                                "' where u_name='"+details.nextToken()+"'";
                        int check=dbHandler.updateTable(query);
                        System.out.println("hello1");
                        output.println(check);
                    }
                }
            }
            else{
                while(true){
                    String choice=input.readLine();
                    if(choice.equals("1")){
                        String buffer=input.readLine();
                        StringTokenizer s1=new StringTokenizer(buffer);
                        uname=s1.nextToken();
                        String password=s1.nextToken();
                        boolean notfound=true;
                        System.out.println(uname+" "+password);
                        for(Driver d1:drivers){
                            if(d1.getuName().equals(uname)&&d1.getPassword().equals(password)){
                                notfound=false;
                                u_id=d1.getDriverID();
                                output.println("successful");
                                System.out.println("success");
                            }
                        }
                        if(notfound)
                            output.println("failed");
                    }
                    else if(choice.equals("2")){
                        String msg;
                        int pid=0;
                        for(Driver p1:drivers){
                            if(p1.getuName().equals(uname)){
                                msg=("Name:"+p1.getName()+" "+"Contact:"+p1.getContactNo()+" "+"Email:"+p1.getEmail()+" "+
                                        "RideCount:"+p1.getRideCount()+" "+"Rating:"+p1.getRating());
                                output.println(msg);
                                pid=p1.getDriverID();
                                System.out.println(msg);                            }
                        }
                        ArrayList<Ride> r2=new ArrayList<>();
                        for(Ride r1:rideArrayList){
                            if(r1.getDriverID()==pid){
                                r2.add(r1);
                            }
                        }
                        for(Ride r1:r2){
                            System.out.println(r1.Diplayride());
                            output.println(r1.Diplayride());
                        }
                        output.println("exit");

                    }
                    else if(choice.equals("3")){
                        System.out.println("hello");
                        while(Main.bookRideArrayList.size()<1){
                        }
                        System.out.println("passenger found");
                        String dname=null;
                        float drating=5;
                        for(Driver d1:drivers){
                            if(d1.getuName().equals(uname)){
                                dname=d1.getName();
                                drating=d1.getRating();
                            }
                        }
                        for(BookRide br:Main.bookRideArrayList){
                            int count=0;
                            if(!br.isAccepted()){
                                br.setDriverName(dname);
                                br.setDriverRating(drating);
                                br.setAccepted(true);
                                br.setDriverID(u_id);
                                output.println(br.getPassengerName()+" "+br.getPickUp()+" "+br.getDropOff());
                                System.out.println(br.getPassengerName()+" "+br.getPickUp()+" "+br.getDropOff());
                                index=count;
                                break;
                            }
                            count++;
                        }
                    }
                    else if(choice.equals("4")){
                        if(Main.bookRideArrayList.get(index).isCancelled()){
                            output.println("no");
                        }
                        else{
                            Main.bookRideArrayList.get(index).setStarted(true);
                            output.println("yes");

                        }
                    }
                    else if(choice.equals("5")){
                        Calendar calobj = Calendar.getInstance();
                        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                        System.out.println(df.format(calobj.getTime()));
                        StringTokenizer st=new StringTokenizer(df.format(calobj.getTime()));
                        String rdate=st.nextToken();
                        Random random=new Random();
                        float distance=random.nextFloat()*30;
                        int rideid=rideArrayList.size()+1;
                        System.out.println(rideid);
                        for(BookRide br: Main.bookRideArrayList){
                            if(br.getDriverID()==u_id){
                                String query="insert into ridehistory(ride_id,u_id,d_id,rdate,distance,pickup,dropoff) values(" +
                                        rideid+","+br.getDriverID()+","+br.getPassengerID()+",to_date('"+rdate+"','DD/MM/YYYY'),"+
                                        distance+",'"+br.getPickUp()+"','"+br.getDropOff()+"')";
                                System.out.println(query);
                                dbHandler.updateTable(query);
                                rideArrayList=dbHandler.getRides();
                                br.setCompleted(true);
                                br.setStarted(false);
                            }
                        }
                    }
                }

            }


        } catch(IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch(IOException e) {

            }
        }

    }
    private String getUserDetails(){
        for(Passenger p1: passengers){
            if(p1.getUserName().equals(uname))
                return p1.getName();
        }
        return null;
    }
}
