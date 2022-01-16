package com.farrukhrzaidi;

public class BookRide {
    private String passengerName,pickUp,dropOff,driverName;
    private float driverRating;
    private boolean isAccepted,isStarted,isCancelled, isCompleted;
    private int driverID, passengerID;
    public BookRide(String passengerName,String pickUp,String dropOff,int passengerID){
        this.passengerName=passengerName;
        this.pickUp=pickUp;
        this.dropOff=dropOff;
        driverName=null;
        driverRating=-1;
        this.passengerID=passengerID;
        isAccepted=isCancelled=isStarted=isCompleted=false;
    }

    public synchronized boolean isCompleted() {
        return isCompleted;
    }

    public synchronized void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public synchronized String getPassengerName() {
        return passengerName;
    }

    public synchronized void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public synchronized String getPickUp() {
        return pickUp;
    }

    public synchronized void setPickUp(String pickUp) {
        this.pickUp = pickUp;
    }

    public synchronized String getDropOff() {
        return dropOff;
    }

    public synchronized void setDropOff(String dropOff) {
        this.dropOff = dropOff;
    }

    public synchronized String getDriverName() {
        return driverName;
    }

    public synchronized void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public synchronized float getDriverRating() {
        return driverRating;
    }

    public synchronized void setDriverRating(float driverRating) {
        this.driverRating = driverRating;
    }

    public synchronized boolean isAccepted() {
        synchronized (this){
            return isAccepted;
        }
    }

    public synchronized int getDriverID() {
        return driverID;
    }

    public synchronized void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public synchronized int getPassengerID() {
        return passengerID;
    }

    public synchronized void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public synchronized void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public synchronized boolean isStarted() {
        return isStarted;
    }

    public synchronized void setStarted(boolean started) {
        isStarted = started;
    }

    public synchronized boolean isCancelled() {
        return isCancelled;
    }

    public synchronized void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
    public synchronized  String getDriverDetails(){
        return driverName+" "+driverRating;
    }
}
