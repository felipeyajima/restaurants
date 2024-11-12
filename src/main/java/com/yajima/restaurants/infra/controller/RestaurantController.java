package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.CreateRestaurant;
import com.yajima.restaurants.application.usecases.ListRestaurants;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final CreateRestaurant createRestaurant;

    private final ListRestaurants listRestaurants;

    public RestaurantController(CreateRestaurant createRestaurant, ListRestaurants listRestaurants) {
        this.createRestaurant = createRestaurant;
        this.listRestaurants = listRestaurants;
    }

    @PostMapping
    public RestaurantDto createRestaurant(@RequestBody RestaurantDto dto){
        Restaurant saved = createRestaurant.createRestaurant(new Restaurant(dto.name(), dto.cnpj(), dto.foodType(), dto.startingHour(), dto.finishingHour()));

        return new RestaurantDto(saved.getName(), saved.getCnpj(), saved.getFoodType(), saved.getStartingHour(), saved.getFinishingHour());
    }

    @GetMapping
    public List<RestaurantDto> listRestaurants(){
        return listRestaurants.getAllRestaurants().stream()
                .map(r -> new RestaurantDto(r.getCnpj(), r.getName(), r.getFoodType(), r.getStartingHour(), r.getFinishingHour())).collect(Collectors.toList());
    }



}
