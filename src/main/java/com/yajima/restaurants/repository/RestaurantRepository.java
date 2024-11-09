package com.yajima.restaurants.repository;

import com.yajima.restaurants.dto.RestaurantDTO;
import com.yajima.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
}
