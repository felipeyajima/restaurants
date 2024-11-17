package com.yajima.restaurants.application.usecases.restaurant;

import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

import java.util.List;

public class ListRestaurantsPerFoodType {

    private final RepositoryOfRestaurant repository;


    public ListRestaurantsPerFoodType(RepositoryOfRestaurant repository) {
        this.repository = repository;
    }

    public List<Restaurant> listByFoodType(String foodType){
        return this.repository.listByFoodType(foodType);
    }

}
