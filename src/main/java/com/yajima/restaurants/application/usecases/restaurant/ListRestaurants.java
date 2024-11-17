package com.yajima.restaurants.application.usecases.restaurant;

import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

import java.util.List;

public class ListRestaurants {

    private final RepositoryOfRestaurant repository;

    public ListRestaurants(RepositoryOfRestaurant repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAllRestaurants(){
        return this.repository.listEveryting();
    }
}
