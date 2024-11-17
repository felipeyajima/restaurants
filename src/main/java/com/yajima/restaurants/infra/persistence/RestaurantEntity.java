package com.yajima.restaurants.infra.persistence;

import com.yajima.restaurants.domain.Address;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    private String postalCode;
    private Integer addressNumber;


    public RestaurantEntity(){}
    public RestaurantEntity(UUID id, String name, String cnpj, String foodType, LocalTime startingHour, LocalTime finishingHour, String postalCode, Integer addressNumber) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.foodType = foodType;
        this.startingHour = startingHour;
        this.finishingHour = finishingHour;
        this.postalCode = postalCode;
        this.addressNumber = addressNumber;
    }
}
