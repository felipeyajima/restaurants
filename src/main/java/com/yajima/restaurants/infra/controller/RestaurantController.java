package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.CreateRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final CreateRestaurant createRestaurant;

    public RestaurantController(CreateRestaurant createRestaurant) {
        this.createRestaurant = createRestaurant;
    }

    @PostMapping
    public RestaurantDto createRestaurant(@RequestBody RestaurantDto dto){
        Restaurant saved = createRestaurant.createRestaurant(new Restaurant(dto.name(), dto.cnpj(), dto.foodType(), dto.startingHour(), dto.finishingHour()));

        return new RestaurantDto(saved.getName(), saved.getCnpj(), saved.getFoodType(), saved.getStartingHour(), saved.getFinishingHour());
    }

}
