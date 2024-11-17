package com.yajima.restaurants.application.usecases.restaurant;

import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

import java.util.List;

public class ListRestaurantsPerAddress {

    private final RepositoryOfRestaurant repository;


    public ListRestaurantsPerAddress(RepositoryOfRestaurant repository) {
        this.repository = repository;
    }

    public List<Restaurant> listByAddress(String address){
        return this.repository.listByAddress(address);
    }
}
