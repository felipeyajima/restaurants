package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

public record TableDto(
        Integer tableNumber,
        Integer numberOfChairs,
        Restaurant restaurant
) {

}
