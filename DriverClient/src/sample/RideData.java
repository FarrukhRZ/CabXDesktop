package sample;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class RideData
{
    private SimpleStringProperty rideID,rideDate,ridePickup, rideDropoff,rideDName, rideFare;
    public RideData(String rideID,String rideDate,String ridePickup,String rideDropoff,String rideDName,String rideFare){
        this.rideID=new SimpleStringProperty(rideID);
        this.rideDate=new SimpleStringProperty(rideDate);
        this.ridePickup=new SimpleStringProperty(ridePickup);
        this.rideDropoff=new SimpleStringProperty(rideDropoff);
        this.rideFare=new SimpleStringProperty(rideFare);
        this.rideDName=new SimpleStringProperty(rideDName);
    }

    public String getRideID() {
        return rideID.get();
    }

    public SimpleStringProperty rideIDProperty() {
        return rideID;
    }

    public void setRideID(String rideID) {
        this.rideID.set(rideID);
    }

    public String getRideDate() {
        return rideDate.get();
    }

    public SimpleStringProperty rideDateProperty() {
        return rideDate;
    }

    public void setRideDate(String rideDate) {
        this.rideDate.set(rideDate);
    }

    public String getRidePickup() {
        return ridePickup.get();
    }

    public SimpleStringProperty ridePickupProperty() {
        return ridePickup;
    }

    public void setRidePickup(String ridePickup) {
        this.ridePickup.set(ridePickup);
    }

    public String getRideDropoff() {
        return rideDropoff.get();
    }

    public SimpleStringProperty rideDropoffProperty() {
        return rideDropoff;
    }

    public void setRideDropoff(String rideDropoff) {
        this.rideDropoff.set(rideDropoff);
    }

    public String getRideDName() {
        return rideDName.get();
    }

    public SimpleStringProperty rideDNameProperty() {
        return rideDName;
    }

    public void setRideDName(String rideDName) {
        this.rideDName.set(rideDName);
    }

    public String getRideFare() {
        return rideFare.get();
    }

    public SimpleStringProperty rideFareProperty() {
        return rideFare;
    }

    public void setRideFare(String rideFare) {
        this.rideFare.set(rideFare);
    }
}
