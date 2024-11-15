package com.yajima.restaurants.domain.entities.table;

public class Table {


    private Integer tableNumber;
    private Integer numberOfChairs;

    public Table(Integer tableNumber, Integer numberOfChairs) {
        this.tableNumber = tableNumber;
        this.numberOfChairs = numberOfChairs;
    }

    public Integer getTableNumber() {
        return tableNumber;
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
