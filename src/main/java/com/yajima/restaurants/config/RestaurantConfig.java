package com.yajima.restaurants.config;

import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.application.usecases.CreateRestaurant;
import com.yajima.restaurants.application.usecases.ListRestaurants;
import com.yajima.restaurants.infra.gateway.RestaurantEntityMapper;
import com.yajima.restaurants.infra.gateway.RestaurantJpaRepository;
import com.yajima.restaurants.infra.persistence.RestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestaurantConfig {
    @Bean
    CreateRestaurant createRestaurant(RepositoryOfRestaurant repositoryOfRestaurant){
        return new CreateRestaurant(repositoryOfRestaurant);
    }

    @Bean
    ListRestaurants listRestaurants(RepositoryOfRestaurant repositoryOfRestaurant){
        return new ListRestaurants(repositoryOfRestaurant);
    }

    @Bean
    RestaurantJpaRepository createJpaRepository(RestaurantRepository repository, RestaurantEntityMapper mapper){
        return new RestaurantJpaRepository(repository, mapper);
    }

    @Bean
    RestaurantEntityMapper returnMapperRestaurant(){
        return new RestaurantEntityMapper();
    }
}
