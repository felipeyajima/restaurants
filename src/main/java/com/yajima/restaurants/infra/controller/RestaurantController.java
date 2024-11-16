package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.CreateRestaurant;
import com.yajima.restaurants.application.usecases.ListRestaurants;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
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
        Restaurant saved = createRestaurant.createRestaurant(new Restaurant(
                dto.getId(),
                dto.getName(),
                dto.getCnpj(),
                dto.getFoodType(),
                dto.getStartingHour(),
                dto.getFinishingHour()

        ));

        return dto;
    }

    @GetMapping
    public List<RestaurantDto> listRestaurants(){
        return listRestaurants.getAllRestaurants().stream()
                .map(r -> new RestaurantDto(
                        r.getId(),
                        r.getName(),
                        r.getCnpj(),
                        r.getFoodType(),
                        r.getStartingHour(),
                        r.getFinishingHour(),
                        r.getTables()
                        )).collect(Collectors.toList());
    }



}
