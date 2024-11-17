package com.yajima.restaurants.application.gateways;


import com.yajima.restaurants.domain.entities.restaurant.Restaurant;

import java.util.List;


public interface RepositoryOfRestaurant {

    Restaurant createRestaurant(Restaurant restaurant);

    List<Restaurant> listEveryting();

    List<Restaurant> listByFoodType(String foodType);

    List<Restaurant> listByName(String name);

    List<Restaurant> listByAddress(String address);


    //public Restaurant findById(UUID id);

    //public void update(Restaurant restaurant);

    //public void delete(UUID id);


}
