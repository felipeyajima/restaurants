package com.yajima.restaurants.domain.entities.restaurant;

import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

public class RestaurantTest {

    @Test
    public void shouldntSaveCnpjWithInvalidFormat(){
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Restaurant(UUID.randomUUID(), "Restaurant", "11.222.333//--5555-66", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32"), "0000000", 12, true));
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Restaurant(UUID.randomUUID(),"Restaurant","11222333555566", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32"), "0000000", 12, true));
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Restaurant(UUID.randomUUID(),"Restaurant","", "Chinese", LocalTime.parse("15:32"), LocalTime.parse("15:32"), "0000000", 12, true));

    }

    @Test
    public void shouldntSavePostalCodeWithInvalidFormat(){
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Restaurant(UUID.randomUUID(), "Restaurant", "11.222.333/5555-66", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32"), "0000000", 12, true));
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Restaurant(UUID.randomUUID(),"Restaurant","11.222.333/5555-66", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32"), "00000-00", 12, true));
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Restaurant(UUID.randomUUID(),"Restaurant","11.222.333/5555-66", "Chinese", LocalTime.parse("15:32"), LocalTime.parse("15:32"), "", 12, true));

    }

    @Test
    void shouldRegisterRestaurant(){

        Restaurant restaurant = new Restaurant(UUID.randomUUID(), "Restaurant", "11.222.333/5555-66", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32"), "00000-000", 12, true);

        Assertions.assertEquals(Restaurant.class, restaurant.getClass());
        Assertions.assertEquals("Restaurant", restaurant.getName());
        Assertions.assertEquals("11.222.333/5555-66", restaurant.getCnpj());
        Assertions.assertEquals("Chinese", restaurant.getFoodType());
        Assertions.assertEquals(LocalTime.parse("15:32"), restaurant.getStartingHour());
        Assertions.assertEquals(LocalTime.parse("15:32"), restaurant.getFinishingHour());
        Assertions.assertEquals("00000-000", restaurant.getPostalCode());
        Assertions.assertEquals(12, restaurant.getAddressNumber());
        Assertions.assertEquals(true, restaurant.getOpenOnlyOnBusinessDay());
    }


    /*
    @Test
    public void shouldCreateRestaurantUsingRestaurantBuilder(){
        RestaurantBuilder factory = new RestaurantBuilder();
        Restaurant restaurant = factory.withNameCnpjOpenningHourFinishingHour("Restaurante do Batata", "00.111.222/3333-44", LocalTime.parse("12:00"), LocalTime.parse("12:00"), "0000000", 12);
        Assertions.assertEquals("Restaurante do Batata", restaurant.getName());

        restaurant = factory.includeAddress("00000-000", 1, "Rua dos Fundos");
        Assertions.assertEquals(1, restaurant.getAddress().getNumber());

    }

     */
}
