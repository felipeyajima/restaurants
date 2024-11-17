package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.CreateRestaurant;
import com.yajima.restaurants.application.usecases.ListRestaurants;
import com.yajima.restaurants.application.usecases.ListTablesPerRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final CreateRestaurant createRestaurant;
    private final ListRestaurants listRestaurants;

    private final ListTablesPerRestaurant listTablesPerRestaurant;

    public RestaurantController(CreateRestaurant createRestaurant, ListRestaurants listRestaurants, ListTablesPerRestaurant listTablesPerRestaurant) {
        this.createRestaurant = createRestaurant;
        this.listRestaurants = listRestaurants;
        this.listTablesPerRestaurant = listTablesPerRestaurant;
    }

    @PostMapping
    public RestaurantDto createRestaurant(@RequestBody RestaurantDto dto){
        Restaurant saved = createRestaurant.createRestaurant(new Restaurant(
                dto.getId(),
                dto.getName(),
                dto.getCnpj(),
                dto.getFoodType(),
                dto.getStartingHour(),
                dto.getFinishingHour(),
                dto.getPostalCode(),
                dto.getAddressNumber()

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
                        r.getPostalCode(),
                        r.getAddressNumber()
                        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}/tables")
    public List<TableDto> ListTablesPerRestaurant(@PathVariable UUID id){
        return listTablesPerRestaurant.listTablesPerRestaurant(id).stream().map(t -> new TableDto(
                t.getId(), t.getTableNumber(), t.getNumberOfChairs(), t.getRestaurant()
        )).collect(Collectors.toList());
    }

}
