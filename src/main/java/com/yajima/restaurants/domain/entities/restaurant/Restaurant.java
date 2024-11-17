package com.yajima.restaurants.domain.entities.restaurant;

import java.time.LocalTime;
import java.util.UUID;

public class Restaurant {
    private UUID id;
    private String name;
    private String cnpj;
    private String foodType;

    private LocalTime startingHour;
    private LocalTime finishingHour;

    private String postalCode;
    private Integer addressNumber;



    public Restaurant(UUID id, String name, String cnpj, String foodType, LocalTime startingHour, LocalTime finishingHour, String postalCode, Integer addressNumber) {
        this.id = id;
        this.name = name;

        /*
        if(cnpj == null || !cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")){
            throw new IllegalArgumentException("invalid cnpj!");
        }

         */

        this.cnpj = cnpj;
        this.foodType = foodType;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
        this.postalCode = postalCode;
        this.addressNumber = addressNumber;

    }


    public Restaurant() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }
}
