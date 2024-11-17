package com.yajima.restaurants.config;

import com.yajima.restaurants.application.gateways.RepositoryOfRestaurant;
import com.yajima.restaurants.application.gateways.RepositoryOfTable;
import com.yajima.restaurants.application.usecases.restaurant.*;
import com.yajima.restaurants.application.usecases.tables.ListTablesPerRestaurant;
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
    ListTablesPerRestaurant listTablesPerRestaurant(RepositoryOfTable repositoryOfTable){
        return new ListTablesPerRestaurant(repositoryOfTable);
    }

    @Bean
    ListRestaurantsPerFoodType listRestaurantsPerFoodType(RepositoryOfRestaurant repositoryOfRestaurant){
        return new ListRestaurantsPerFoodType(repositoryOfRestaurant);
    }

    @Bean
    ListRestaurantsPerName listRestaurantsPerName(RepositoryOfRestaurant repositoryOfRestaurant){
        return new ListRestaurantsPerName(repositoryOfRestaurant);
    }

    @Bean
    ListRestaurantsPerAddress listRestaurantsPerAddress(RepositoryOfRestaurant repositoryOfRestaurant){
        return new ListRestaurantsPerAddress(repositoryOfRestaurant);
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
