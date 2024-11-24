package com.yajima.restaurants.domain.entities.table;


import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.UUID;

public class TableTest {

    @Test
    void shouldRegisterTable(){

        Restaurant restaurant1 = new Restaurant(UUID.randomUUID(), "Restaurant", "00.000.000/0000-00","chinese", LocalTime.parse("12:00"), LocalTime.parse("18:00"), "00000-000", 2, true);
        Table table1 = new Table(UUID.randomUUID(),10, 5,"available", restaurant1);

        Assertions.assertEquals(Table.class, table1.getClass());
        Assertions.assertEquals(10, table1.getTableNumber());
        Assertions.assertEquals(5, table1.getNumberOfChairs());
        Assertions.assertEquals("available", table1.getStatus());
        Assertions.assertEquals(Restaurant.class, table1.getRestaurant().getClass());
    }

}




