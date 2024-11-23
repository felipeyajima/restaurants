package com.yajima.restaurants.application.gateways;


import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

import java.util.List;
import java.util.UUID;


public interface RepositoryOfRestaurant {

    Restaurant createRestaurant(Restaurant restaurant);

    List<Restaurant> listEveryting();

    Restaurant findRestaurant(UUID id);

    void deleteRestaurant(UUID id);

    List<Restaurant> listByFoodType(String foodType);

    List<Restaurant> listByName(String name);

    List<Restaurant> listByAddress(String address);


    //public Restaurant findById(UUID id);

    //public void update(Restaurant restaurant);

    //public void delete(UUID id);


}
