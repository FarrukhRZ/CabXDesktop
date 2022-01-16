package com.farrukhrzaidi;

public class Fare {
    private float basePrice;
    private float peak;
    private float totalFare;

    public Fare(float basePrice, float peak, float totalFare) {
        this.basePrice = basePrice;
        this.peak = peak;
        this.totalFare = totalFare;
    }

    public Fare(){
        this(0,0,0);
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public float getPeak() {
        return peak;
    }

    public void setPeak(float peak) {
        this.peak = peak;
    }

    public float getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(float totalFare) {
        this.totalFare = totalFare;
    }
}
