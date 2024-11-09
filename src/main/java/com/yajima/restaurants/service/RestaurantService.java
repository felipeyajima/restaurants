package com.yajima.restaurants.service;

import com.yajima.restaurants.dto.RestaurantDTO;
import com.yajima.restaurants.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RestaurantService {

    Page<RestaurantDTO> findAll(Pageable pageable);

    public RestaurantDTO findById(UUID id);

    public RestaurantDTO save(RestaurantDTO restaurantDTO);

    public RestaurantDTO update(UUID id, RestaurantDTO restaurantDTO);

    public void delete(UUID id);

    public RestaurantDTO toDTO(Restaurant restaurant);

    public Restaurant toEntity(RestaurantDTO restaurantDTO);



}
