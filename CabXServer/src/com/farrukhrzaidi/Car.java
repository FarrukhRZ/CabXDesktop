package com.farrukhrzaidi;

public class Car {
    private String name;
    private int model;
    private int registration;

    public Car(String name, int model, int registration) {
        this.name = name;
        this.model = model;
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }
}
