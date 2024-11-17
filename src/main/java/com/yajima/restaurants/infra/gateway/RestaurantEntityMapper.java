package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.infra.persistence.RestaurantEntity;


public class RestaurantEntityMapper {

    public RestaurantEntity toEntity(Restaurant restaurant){
        return new RestaurantEntity(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getCnpj(),
                restaurant.getFoodType(),
                restaurant.getStartingHour(),
                restaurant.getFinishingHour(),
                restaurant.getPostalCode(),
                restaurant.getAddressNumber()
                );

    }

    public Restaurant toDomain(RestaurantEntity entity){
        return new Restaurant(
                entity.getId(),
                entity.getName(),
                entity.getCnpj(),
                entity.getFoodType(),
                entity.getStartingHour(),
                entity.getFinishingHour(),
                entity.getPostalCode(),
                entity.getAddressNumber()
                );
    }


}
