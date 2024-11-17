package com.yajima.restaurants.infra.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="table_db")
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer tableNumber;
    private Integer numberOfChairs;


    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private RestaurantEntity restaurantEntity;


    public TableEntity() {
    }

    public TableEntity(Integer tableNumber, Integer numberOfChairs, RestaurantEntity restaurantEntity) {
        this.tableNumber = tableNumber;
        this.numberOfChairs = numberOfChairs;
        this.restaurantEntity = restaurantEntity;
    }


}
