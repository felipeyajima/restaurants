package com.yajima.restaurants.infra.persistence;

import com.yajima.restaurants.domain.Address;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name="restaurant_tb")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String cnpj;
    private String foodType;
    private LocalTime startingHour;
    private LocalTime finishingHour;


    public RestaurantEntity(){}
    public RestaurantEntity(String name, String cnpj, String foodType, LocalTime startingHour, LocalTime finishingHour) {
        this.name = name;
        this.cnpj = cnpj;
        this.foodType = foodType;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
    }
}
