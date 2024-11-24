package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.restaurant.*;
import com.yajima.restaurants.application.usecases.tables.ListTablesPerRestaurant;
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

    private final FindRestaurant findRestaurant;

    private final DeleteRestaurant deleteRestaurant;

    private final ListTablesPerRestaurant listTablesPerRestaurant;
    private final ListRestaurantsPerFoodType listRestaurantsPerFoodType;

    private final ListRestaurantsPerName listRestaurantsPerName;

    private final ListRestaurantsPerAddress listRestaurantsPerAddress;

    public RestaurantController(CreateRestaurant createRestaurant, ListRestaurants listRestaurants, FindRestaurant findRestaurant, DeleteRestaurant deleteRestaurant, ListTablesPerRestaurant listTablesPerRestaurant, ListRestaurantsPerFoodType listRestaurantsPerFoodType, ListRestaurantsPerName listRestaurantsPerName, ListRestaurantsPerAddress listRestaurantsPerAddress) {
        this.createRestaurant = createRestaurant;
        this.listRestaurants = listRestaurants;
        this.findRestaurant = findRestaurant;
        this.deleteRestaurant = deleteRestaurant;
        this.listTablesPerRestaurant = listTablesPerRestaurant;
        this.listRestaurantsPerFoodType = listRestaurantsPerFoodType;
        this.listRestaurantsPerName = listRestaurantsPerName;
        this.listRestaurantsPerAddress = listRestaurantsPerAddress;
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
                dto.getAddressNumber(),
                dto.getOpenOnlyOnBusinessDay()

        ));

        return new RestaurantDto(saved.getId(), saved.getName(), saved.getCnpj(), saved.getFoodType(), saved.getStartingHour(), saved.getFinishingHour(), saved.getPostalCode(), saved.getAddressNumber(), saved.getOpenOnlyOnBusinessDay());
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
                        r.getAddressNumber(),
                        r.getOpenOnlyOnBusinessDay()
                        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RestaurantDto findRestaurant(@PathVariable UUID id){
        Restaurant restaurant = findRestaurant.findRestaurant(id);
        return new RestaurantDto(restaurant.getId(), restaurant.getName(), restaurant.getCnpj(), restaurant.getFoodType(), restaurant.getStartingHour(), restaurant.getFinishingHour(), restaurant.getPostalCode(), restaurant.getAddressNumber(), restaurant.getOpenOnlyOnBusinessDay());
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable UUID id){
        deleteRestaurant.deleteRestaurant(id);
    }


    @GetMapping("/{id}/tables")
    public List<TableDto> ListTablesPerRestaurant(@PathVariable UUID id){
        return listTablesPerRestaurant.listTablesPerRestaurant(id).stream().map(t -> new TableDto(
                t.getId(), t.getTableNumber(), t.getNumberOfChairs(), t.getStatus(), t.getRestaurant()
        )).collect(Collectors.toList());
    }

    @GetMapping("/foodtype")
    public List<RestaurantDto> ListRestaurantPerFoodType(@RequestParam("foodtype") String foodtype){
        return listRestaurantsPerFoodType.listByFoodType(foodtype).stream().map(r -> new RestaurantDto(
                r.getId(),
                r.getName(),
                r.getCnpj(),
                r.getFoodType(),
                r.getStartingHour(),
                r.getFinishingHour(),
                r.getPostalCode(),
                r.getAddressNumber(),
                r.getOpenOnlyOnBusinessDay()
        )).collect(Collectors.toList());
    }

    @GetMapping("/name")
    public List<RestaurantDto> ListRestaurantPerName(@RequestParam("name") String name){
        return listRestaurantsPerName.listByName(name).stream().map(r -> new RestaurantDto(
                r.getId(),
                r.getName(),
                r.getCnpj(),
                r.getFoodType(),
                r.getStartingHour(),
                r.getFinishingHour(),
                r.getPostalCode(),
                r.getAddressNumber(),
                r.getOpenOnlyOnBusinessDay()
        )).collect(Collectors.toList());
    }
    @GetMapping("/zipcode")
    public List<RestaurantDto> ListRestaurantPerAddress(@RequestParam("zipcode") String address){
        return listRestaurantsPerAddress.listByAddress(address).stream().map(r -> new RestaurantDto(
                r.getId(),
                r.getName(),
                r.getCnpj(),
                r.getFoodType(),
                r.getStartingHour(),
                r.getFinishingHour(),
                r.getPostalCode(),
                r.getAddressNumber(),
                r.getOpenOnlyOnBusinessDay()
        )).collect(Collectors.toList());
    }

}
