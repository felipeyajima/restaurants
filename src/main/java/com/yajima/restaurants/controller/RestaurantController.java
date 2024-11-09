package com.yajima.restaurants.controller;


import com.yajima.restaurants.dto.RestaurantDTO;
import com.yajima.restaurants.model.Restaurant;
import com.yajima.restaurants.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantDTO>> findAll(Pageable pageable){
        Page<RestaurantDTO> restaurantsDTO = this.restaurantService.findAll(pageable);
        return ResponseEntity.ok(restaurantsDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> findById(@PathVariable UUID id){
        RestaurantDTO restaurantDTO = restaurantService.findById(id);
        return ResponseEntity.ok(restaurantDTO);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> save(@RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO savedRestaurant = restaurantService.save(restaurantDTO);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> update(@PathVariable UUID id, @RequestBody RestaurantDTO restaurantDTO){
        RestaurantDTO updatedRestaurant = restaurantService.update(id, restaurantDTO);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
