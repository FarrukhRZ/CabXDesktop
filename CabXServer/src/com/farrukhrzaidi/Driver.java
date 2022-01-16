package com.farrukhrzaidi;

import java.sql.Date;

public class Driver {
    private int driverID;
    private String uName;
    private String password;
    private float rating;
    private long contactNo;
    private String eMail;
    private int rideCount;
    private Date reg_date;
    private String name;
    private long nic;
    private Car car;

    public Driver(int driverID, String uName, String password, float rating, long contactNo,
                  String eMail, int rideCount, Date reg_date, String name, long nic, Car car) {
        this.driverID = driverID;
        this.uName = uName;
        this.password = password;
        this.rating = rating;
        this.contactNo = contactNo;
        this.eMail = eMail;
        this.rideCount = rideCount;
        this.reg_date = reg_date;
        this.name = name;
        this.nic = nic;
        this.car=car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
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

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
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
}
