package com.yajima.restaurants.infra.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Builder
@Table(name="table_db")
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer tableNumber;
    private Integer numberOfChairs;
    private String status;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private RestaurantEntity restaurantEntity;


    public TableEntity() {
    }

    public TableEntity(UUID id,Integer tableNumber, Integer numberOfChairs, String status, RestaurantEntity restaurantEntity) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.numberOfChairs = numberOfChairs;
        this.status = status;
        this.restaurantEntity = restaurantEntity;

    }


}
