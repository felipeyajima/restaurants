package com.yajima.restaurants.domain.entities.restaurant;

import com.yajima.restaurants.domain.Address;

import java.time.LocalTime;

public class Restaurant {
    private String name;
    private String cnpj;
    private String foodType;

    private Address address;
    private LocalTime startingHour;
    private LocalTime finishingHour;

    public Restaurant(String name, String cnpj, String foodType, LocalTime startingHour, LocalTime finishingHour) {
        this.name = name;

        if(cnpj == null || !cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")){
            throw new IllegalArgumentException("invalid cnpj!");
        }

        this.cnpj = cnpj;
        this.foodType = foodType;
        //this.address = address;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalTime getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(LocalTime startingHour) {
        this.startingHour = startingHour;
    }

    public LocalTime getFinishingHour() {
        return finishingHour;
    }

    public void setFinishingHour(LocalTime finishingHour) {
        this.finishingHour = finishingHour;
    }
}
