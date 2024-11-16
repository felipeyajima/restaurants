package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.domain.entities.table.Table;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantDto{
    private UUID id;
    private String name;
    private String cnpj;
    private String foodType;
    //private Address address,
    private LocalTime startingHour;
    private LocalTime finishingHour;

    private List<Table> tables;


    public RestaurantDto(UUID id, String name, String cnpj, String foodType, LocalTime startingHour, LocalTime finishingHour, List<Table> tables) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.foodType = foodType;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
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
}
