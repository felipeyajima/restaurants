package com.yajima.restaurants.application.usecases.restaurant;

import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

import java.util.UUID;

public class FindRestaurant {
    public final RepositoryOfRestaurant repository;


    public FindRestaurant(RepositoryOfRestaurant repository) {
        this.repository = repository;
    }

    public Restaurant findRestaurant(UUID id) {
        return this.repository.findRestaurant(id);
    }

}
