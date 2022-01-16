package com.farrukhrzaidi;

import java.sql.Date;

public class UserHistory {
    int totalRides;
    Date registrationDate;
    Ride[] rides;

    public UserHistory(int totalRides, Date registrationDate
            , Ride[] rides) {
        this.totalRides = totalRides;
        this.registrationDate = registrationDate;
        this.rides = rides;
    }

    public int getTotalRides() {
        return totalRides;
    }

    public void setTotalRides(int totalRides) {
        this.totalRides = totalRides;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Ride[] getRides() {
        return rides;
    }

    public void setRides(Ride[] rides) {
        this.rides = rides;
    }
}
