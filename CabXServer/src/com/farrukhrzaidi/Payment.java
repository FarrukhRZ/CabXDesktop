package com.farrukhrzaidi;

public class Payment {
    String type;
    float Amount;

    public Payment(String type, float amount) {
        this.type = type;
        Amount = amount;
    }
    public Payment(){
        this("",0);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }
}
