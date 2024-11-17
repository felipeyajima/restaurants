package com.yajima.restaurants.application.usecases.restaurant;

import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

import java.util.List;

public class ListRestaurantsPerName {

    private final RepositoryOfRestaurant repository;


    public ListRestaurantsPerName(RepositoryOfRestaurant repository) {
        this.repository = repository;
    }


    public List<Restaurant> listByName(String name){
        return this.repository.listByName(name);
    }
}
