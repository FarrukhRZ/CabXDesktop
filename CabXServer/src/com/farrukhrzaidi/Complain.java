package com.farrukhrzaidi;

import java.sql.Date;

public class Complain {
    private String subject;
    private String body;
    private int passengerID;
    private int driverID;
    Date complaintDate;

    public Complain(String subject, String body, int passengerID, int driverID, Date complaintDate) {
        this.subject = subject;
        this.body = body;
        this.passengerID = passengerID;
        this.driverID = driverID;
        this.complaintDate = complaintDate;
    }
    public Complain(){
        this("","",0,0,null);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public Date getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Date complaintDate) {
        this.complaintDate = complaintDate;
    }
}
