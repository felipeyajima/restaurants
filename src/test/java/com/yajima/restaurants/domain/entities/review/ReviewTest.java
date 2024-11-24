package com.yajima.restaurants.domain.entities.review;

import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class ReviewTest {

    @Test
    void shouldRegisterReview(){
        Customer customer1 = new Customer(UUID.randomUUID(), "John", "333.333.333-00", "teste@teste.com.br");
        Restaurant restaurant1 = new Restaurant(UUID.randomUUID(), "Restaurant", "00.000.000/0000-00","chinese", LocalTime.parse("12:00"), LocalTime.parse("18:00"), "00000-000", 2, true);
        Table table1 = new Table(UUID.randomUUID(),10, 5,"available", restaurant1);
        Booking booking1 = new Booking(UUID.randomUUID(), "reserved", LocalDateTime.parse("2024-11-22T12:15:30"),LocalDateTime.parse("2024-11-22T16:15:30"), table1, customer1);
        Review review1 = new Review(UUID.randomUUID(),5, "Delicious food", LocalDateTime.parse("2024-11-22T16:15:30"), booking1);


        Assertions.assertEquals(Review.class, review1.getClass());
        Assertions.assertEquals(5, review1.getNumberOfStars());
        Assertions.assertEquals("Delicious food", review1.getMessage());
        Assertions.assertEquals(LocalDateTime.parse("2024-11-22T16:15:30"), review1.getDateOfReview());
        Assertions.assertEquals(Booking.class, review1.getBooking().getClass());
    }

}
