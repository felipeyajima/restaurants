package com.yajima.restaurants.application.usecases.restaurant;

import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;

import java.util.UUID;

public class DeleteRestaurant {
    public final RepositoryOfRestaurant repository;


    public DeleteRestaurant(RepositoryOfRestaurant repository) {
        this.repository = repository;
    }

    public void deleteRestaurant(UUID id){this.repository.deleteRestaurant(id);}
}
