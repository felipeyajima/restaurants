package com.yajima.restaurants.infra.controller;

import java.time.LocalTime;

import java.util.UUID;

public class RestaurantDto{
    private UUID id;
    private String name;
    private String cnpj;
    private String foodType;
    //private Address address,
    private LocalTime startingHour;
    private LocalTime finishingHour;
    private String postalCode;
    private Integer addressNumber;

    private Boolean openOnlyOnBusinessDay;


    public RestaurantDto(UUID id, String name, String cnpj, String foodType, LocalTime startingHour, LocalTime finishingHour, String postalCode, Integer addressNumber, Boolean openOnlyOnBusinessDay) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.foodType = foodType;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
        this.postalCode = postalCode;
        this.addressNumber = addressNumber;
        this.openOnlyOnBusinessDay = openOnlyOnBusinessDay;

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

    public Boolean getOpenOnlyOnBusinessDay() {
        return openOnlyOnBusinessDay;
    }

    public void setOpenOnlyOnBusinessDay(Boolean openOnlyOnBusinessDay) {
        this.openOnlyOnBusinessDay = openOnlyOnBusinessDay;
    }
}
