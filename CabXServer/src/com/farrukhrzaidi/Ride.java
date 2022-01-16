package com.farrukhrzaidi;

import java.sql.Date;

public class Ride {
    private int rideID;
    private int passengerID;
    private int driverID;
    private String pickup;
    private String dropOff;
    private Date rideDate;
    private Fare fare;
    private Float dRating;
    private Float pRating;
    private Float distance;

    public Ride(int rideID,int passengerID,int driverID, Date rideDate, Float distance,String pickup, String dropOff, Fare fare,
                Float dRating, Float pRating) {
        this.rideID = rideID;
        this.pickup = pickup;
        this.dropOff = dropOff;
        this.rideDate = rideDate;
        this.fare = fare;
        this.dRating = dRating;
        this.pRating = pRating;
        this.distance = distance;
        this.passengerID=passengerID;
        this.driverID=driverID;
    }

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDropOff() {
        return dropOff;
    }

    public void setDropOff(String dropOff) {
        this.dropOff = dropOff;
    }

    public Date getRideDate() {
        return rideDate;
    }

    public void setRideDate(Date rideDate) {
        this.rideDate = rideDate;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public Float getDRating() {
        return dRating;
    }

    public void setDRating(Float dRating) {
        this.dRating = dRating;
    }

    public Float getPRating() {
        return pRating;
    }

    public void setPRating(Float pRating) {
        this.pRating = pRating;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }
    public String Diplayride(){
        return rideID+" "+rideDate+" "+pickup+" "+dropOff+" "+fare.getTotalFare()+" "+driverID;
    }
}
