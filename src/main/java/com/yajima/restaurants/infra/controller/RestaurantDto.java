package com.yajima.restaurants.infra.controller;

import java.time.LocalTime;

public record RestaurantDto(
        String name,
        String cnpj,
        String foodType,
        //private Address address,
        LocalTime startingHour,
        LocalTime finishingHour
) {

}
