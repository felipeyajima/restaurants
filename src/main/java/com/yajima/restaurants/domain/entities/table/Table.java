package com.yajima.restaurants.domain.entities.table;

import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

public class Table {


    private Integer tableNumber;
    private Integer numberOfChairs;

    private Restaurant restaurant;

    public Table(Integer tableNumber, Integer numberOfChairs, Restaurant restaurant) {
        this.tableNumber = tableNumber;
        this.numberOfChairs = numberOfChairs;
        this.restaurant = restaurant;
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
