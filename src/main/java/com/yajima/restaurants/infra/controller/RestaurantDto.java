package com.yajima.restaurants.infra.controller;

import java.time.LocalTime;
import java.util.UUID;

public record RestaurantDto(
        UUID id,
        String name,
        String cnpj,
        String foodType,
        //private Address address,
        LocalTime startingHour,
        LocalTime finishingHour
) {

}
