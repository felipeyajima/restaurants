package com.yajima.restaurants.infra.gateway;



import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;
import com.yajima.restaurants.infra.persistence.RestaurantEntity;
import com.yajima.restaurants.infra.persistence.RestaurantRepository;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class RestaurantJpaRepository implements RepositoryOfRestaurant {

    private final RestaurantRepository repository;
    private final RestaurantEntityMapper mapper;

    public RestaurantJpaRepository(RestaurantRepository repository, RestaurantEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        RestaurantEntity entity = mapper.toEntity(restaurant);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Restaurant> listEveryting() {

        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());

    }

    @Override
    public Restaurant findRestaurant(UUID id) {
        RestaurantEntity restaurant = repository.findById(id).orElseThrow(()-> new ControllerSystemException("restaurant not found"));
        return mapper.toDomain(restaurant);
    }

    @Override
    public void deleteRestaurant(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Restaurant> listByFoodType(String foodType) {
        return repository.findByFoodType(foodType).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> listByName(String name) {
        return repository.findByName(name).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> listByAddress(String address) {
        return repository.findByPostalCode(address).stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
