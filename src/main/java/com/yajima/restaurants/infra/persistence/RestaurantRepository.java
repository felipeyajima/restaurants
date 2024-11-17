package com.yajima.restaurants.infra.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {

    List<RestaurantEntity> findByFoodType(String foodType);

}
