package com.yajima.restaurants.domain.entities.restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class RestaurantTest {
    @Test
    public void shouldntSaveCnpjWithInvalidFormat(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Restaurant("Crazy Restaurant","11.222.333//--5555-66", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Restaurant("Crazy Restaurant","11222333555566", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32")));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Restaurant("Crazy Restaurant","", "Chinese", LocalTime.parse("15:32"), LocalTime.parse("15:32")));
    }

    @Test
    public void shouldCreateRestaurantUsingRestaurantBuilder(){
        RestaurantBuilder factory = new RestaurantBuilder();
        Restaurant restaurant = factory.withNameCnpjOpenningHourFinishingHour("Restaurante do Batata", "00.111.222/3333-44", LocalTime.parse("12:00"), LocalTime.parse("12:00"));
        Assertions.assertEquals("Restaurante do Batata", restaurant.getName());

        restaurant = factory.includeAddress("00000-000", 1, "Rua dos Fundos");
        Assertions.assertEquals(1, restaurant.getAddress().getNumber());

    }
}
