package com.farrukhrzaidi;
public class Passenger {
    private int passengerID;
    private String userName;
    private String password;
    private float rating;
    private long contactNo;
    private String eMail;
    private int rideCount;
    private int credit;
    private String name;
    private long nic;
    private UserHistory history;

    public Passenger(int passengerID, String userName, String password, float rating, long contactNo,
                     String eMail, int rideCount, int credit, String name, long nic) {
        this.passengerID = passengerID;
        this.userName = userName;
        this.password = password;
        this.rating = rating;
        this.contactNo = contactNo;
        this.eMail = eMail;
        this.rideCount = rideCount;
        this.credit = credit;
        this.name = name;
        this.nic = nic;
        //history.setTotalRides(rideCount);
    }

    public UserHistory getHistory() {
        return history;
    }

    public void setHistory(UserHistory history) {
        this.history = history;
    }

    public void display(){
        System.out.println(passengerID+" "+userName+" "+password+" "+rating+" "+contactNo+" "+eMail+" "+rideCount+" "+credit+" "+name+" "+nic);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNic() {
        return nic;
    }

    public void setNic(long nic) {
        this.nic = nic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return eMail;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public int getRideCount() {
        return rideCount;
    }

    public void setRideCount(int rideCount) {
        this.rideCount = rideCount;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
