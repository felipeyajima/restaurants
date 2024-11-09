package com.yajima.restaurants.service.impl;

import com.yajima.restaurants.controller.exception.ControllerNotFoundException;
import com.yajima.restaurants.dto.RestaurantDTO;
import com.yajima.restaurants.model.Restaurant;
import com.yajima.restaurants.repository.RestaurantRepository;
import com.yajima.restaurants.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public Page<RestaurantDTO> findAll(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        return restaurants.map(this::toDTO);
    }

    @Override
    public RestaurantDTO findById(UUID id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("restaurant not found"));
        return toDTO(restaurant);
    }

    @Override
    public RestaurantDTO save(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = toEntity(restaurantDTO);
        restaurant = restaurantRepository.save(restaurant);
        return toDTO(restaurant);
    }

    @Override
    public RestaurantDTO update(UUID id, RestaurantDTO restaurantDTO) {
        try {
            Restaurant restaurant = restaurantRepository.getReferenceById(id);
            restaurant.setName(restaurantDTO.name());
            restaurant.setAddress(restaurantDTO.address());
            restaurant.setFoodType(restaurantDTO.foodType());
            restaurant.setStartingHour(restaurantDTO.startingHour());
            restaurant.setFinishingHour(restaurantDTO.finishingHour());

            restaurant = restaurantRepository.save(restaurant);

            return toDTO(restaurant);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("restaurant not found");
        }
    }

    @Override
    public void delete(UUID id) {
        restaurantRepository.deleteById(id);
    }


    public RestaurantDTO toDTO(Restaurant restaurant){
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getFoodType(),
                restaurant.getAddress(),
                restaurant.getStartingHour(),
                restaurant.getFinishingHour()
        );
    }

    public Restaurant toEntity(RestaurantDTO restaurantDTO){
        return new Restaurant(
                restaurantDTO.id(),
                restaurantDTO.name(),
                restaurantDTO.address(),
                restaurantDTO.foodType(),
                restaurantDTO.startingHour(),
                restaurantDTO.finishingHour()
        );
    }

}
