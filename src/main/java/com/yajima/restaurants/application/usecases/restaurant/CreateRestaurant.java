package com.yajima.restaurants.application.usecases.restaurant;


import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

public class CreateRestaurant  {

    private final RepositoryOfRestaurant repository;

    public CreateRestaurant(RepositoryOfRestaurant repository) {
        this.repository = repository;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {

        return repository.createRestaurant(restaurant);
    }
}
