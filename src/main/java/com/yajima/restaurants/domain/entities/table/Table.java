package com.yajima.restaurants.domain.entities.table;

import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

import java.util.UUID;

public class Table {

    private UUID id;

    private Integer tableNumber;
    private Integer numberOfChairs;

    private Restaurant restaurant;

    public Table(UUID id, Integer tableNumber, Integer numberOfChairs, Restaurant restaurant) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.numberOfChairs = numberOfChairs;
        this.restaurant = restaurant;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getNumberOfChairs() {
        return numberOfChairs;
    }

    public void setNumberOfChairs(Integer numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
    }
}
