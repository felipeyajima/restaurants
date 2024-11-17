package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import com.yajima.restaurants.infra.persistence.RestaurantEntity;
import com.yajima.restaurants.infra.persistence.TableEntity;

public class TableEntityMapper {

    public TableEntity toEntity(Table table){
        return new TableEntity(
                table.getTableNumber(),
                table.getNumberOfChairs(),
                new RestaurantEntity(
                        table.getRestaurant().getId(),
                        table.getRestaurant().getName(),
                        table.getRestaurant().getCnpj(),
                        table.getRestaurant().getFoodType(),
                        table.getRestaurant().getStartingHour(),
                        table.getRestaurant().getFinishingHour(),
                        table.getRestaurant().getPostalCode(),
                        table.getRestaurant().getAddressNumber()
                )


        );
    }

    public Table toDomain(TableEntity entity){
        return new Table(
                entity.getId(),
                entity.getTableNumber(),
                entity.getNumberOfChairs(),
                new Restaurant(
                        entity.getRestaurantEntity().getId(),
                        entity.getRestaurantEntity().getName(),
                        entity.getRestaurantEntity().getCnpj(),
                        entity.getRestaurantEntity().getFoodType(),
                        entity.getRestaurantEntity().getStartingHour(),
                        entity.getRestaurantEntity().getFinishingHour(),
                        entity.getRestaurantEntity().getPostalCode(),
                        entity.getRestaurantEntity().getAddressNumber()
                )
        );
    }


}
