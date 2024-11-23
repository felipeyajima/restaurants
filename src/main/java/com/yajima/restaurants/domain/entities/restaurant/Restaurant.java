package com.yajima.restaurants.domain.entities.restaurant;

import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;
import jakarta.validation.constraints.NotNull;

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

        if(cnpj == null || !cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")){
            throw new ControllerSystemException("invalid cnpj. should be 00.000.000/0000-00");
        }



        this.cnpj = cnpj;

        this.foodType = foodType;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;

        if(postalCode == null || !postalCode.matches("\\d{5}\\-\\d{3}")){
            throw new ControllerSystemException("invalid zip code. Should be 000000-000!");
        }



        this.postalCode = postalCode;

        this.addressNumber = addressNumber;

    }

    public Restaurant() {
    }
/*
    public Restaurant(String name, String cnpj, String foodType, LocalTime startingHour, LocalTime finishingHour, String postalCode, Integer addressNumber) {

        this.name = name;

        if(cnpj == null || !cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")){
            throw new ControllerSystemException("invalid cnpj!");
        }


        this.cnpj = cnpj;
        this.foodType = foodType;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
        this.postalCode = postalCode;
        this.addressNumber = addressNumber;
    }

 */


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
