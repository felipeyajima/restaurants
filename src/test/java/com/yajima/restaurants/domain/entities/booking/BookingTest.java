package com.yajima.restaurants.domain.entities.booking;

import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class BookingTest {

    @Test
    public void shouldntSaveBookingWithIncompatibleRestaurantWorkTime(){
        Customer customer = new Customer(UUID.randomUUID(), "John", "333.333.333-00", "teste@teste.com.br");
        Restaurant restaurant = new Restaurant(UUID.randomUUID(), "Restaurant", "00.000.000/0000-00","chinese", LocalTime.parse("12:00"), LocalTime.parse("18:00"), "00000-000", 2, true);
        Table table = new Table(UUID.randomUUID(),10, 5,"available", restaurant);

        // Customer trying to booking a table when the restaurant is not open yet
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Booking(UUID.randomUUID(), "reserved",LocalDateTime.parse("2024-11-22T10:15:30"),LocalDateTime.parse("2024-11-22T16:15:30"), table, customer));
        // Customer trying to booking a table when the restaurant will be closed
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Booking(UUID.randomUUID(), "reserved",LocalDateTime.parse("2024-11-22T13:15:30"),LocalDateTime.parse("2024-11-22T19:15:30"), table, customer));

        // Customer trying to booking a table on Weekend and the restaurant is no able to work unless Weekdays
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Booking(UUID.randomUUID(), "reserved",LocalDateTime.parse("2024-11-24T13:15:30"),LocalDateTime.parse("2024-11-24T16:15:30"), table, customer));


    }

    @Test
    void shouldRegisterBooking(){
        Customer customer1 = new Customer(UUID.randomUUID(), "John", "333.333.333-00", "teste@teste.com.br");
        Restaurant restaurant1 = new Restaurant(UUID.randomUUID(), "Restaurant", "00.000.000/0000-00","chinese", LocalTime.parse("12:00"), LocalTime.parse("18:00"), "00000-000", 2, true);
        Table table1 = new Table(UUID.randomUUID(),10, 5,"available", restaurant1);
        Booking booking1 = new Booking(UUID.randomUUID(), "reserved",LocalDateTime.parse("2024-11-22T12:15:30"),LocalDateTime.parse("2024-11-22T16:15:30"), table1, customer1);

        Assertions.assertEquals(Booking.class, booking1.getClass());
        Assertions.assertEquals("reserved", booking1.getStatus());
        Assertions.assertEquals(LocalDateTime.parse("2024-11-22T12:15:30"), booking1.getBookingStart());
        Assertions.assertEquals(LocalDateTime.parse("2024-11-22T16:15:30"), booking1.getBookingFinish());
        Assertions.assertEquals(table1.getClass(), booking1.getTable().getClass());
        Assertions.assertEquals(customer1.getClass(), booking1.getCustomer().getClass());

    }

}
