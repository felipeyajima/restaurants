package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.infra.persistence.RestaurantEntity;


public class RestaurantEntityMapper {

    public RestaurantEntity toEntity(Restaurant restaurant){
        return new RestaurantEntity(
                restaurant.getName(),
                restaurant.getCnpj(),
                restaurant.getFoodType(),
                restaurant.getStartingHour(),
                restaurant.getFinishingHour());
    }

    public Restaurant toDomain(RestaurantEntity entity){
        return new Restaurant(
                entity.getName(),
                entity.getCnpj(),
                entity.getFoodType(),
                entity.getStartingHour(),
                entity.getFinishingHour()
                );
    }


}
