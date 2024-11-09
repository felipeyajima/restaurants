package com.yajima.restaurants.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="restaurant_tb")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String foodType;

    private String address;

    private LocalDateTime startingHour;

    private LocalDateTime finishingHour;

    public Restaurant(){
    }

    public Restaurant(UUID id, String name, String foodType, String address, LocalDateTime startingHour, LocalDateTime finishingHour){
        this.id = id;
        this.name = name;
        this.address = address;
        this.foodType = foodType;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
    }

}
