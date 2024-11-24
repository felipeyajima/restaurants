package com.yajima.restaurants.infra.persistence;

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
    private String postalCode;
    private Integer addressNumber;
    private Boolean openOnlyOnBusinessDay;


    public RestaurantEntity(){}
    public RestaurantEntity(UUID id, String name, String cnpj, String foodType, LocalTime startingHour, LocalTime finishingHour, String postalCode, Integer addressNumber, boolean openOnlyOnBusinessDay) {
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
}
