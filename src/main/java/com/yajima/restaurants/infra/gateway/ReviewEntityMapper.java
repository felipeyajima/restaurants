package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.review.Review;
import com.yajima.restaurants.domain.entities.table.Table;
import com.yajima.restaurants.infra.persistence.*;

import java.time.LocalTime;

public class ReviewEntityMapper {

    public ReviewEntity toEntity(Review review){
        return new ReviewEntity(
                review.getId(),
                review.getNumberOfStars(),
                review.getMessage(),
                review.getDateOfReview(),
                new BookingEntity(
                        review.getBooking().getId(),
                        review.getBooking().getStatus(),
                        review.getBooking().getBookingStart(),
                        review.getBooking().getBookingFinish(),
                        new TableEntity(
                                review.getBooking().getTable().getId(),
                                review.getBooking().getTable().getTableNumber(),
                                review.getBooking().getTable().getNumberOfChairs(),
                                review.getBooking().getTable().getStatus(),
                                new RestaurantEntity(
                                        review.getBooking().getTable().getRestaurant().getId(),
                                        review.getBooking().getTable().getRestaurant().getName(),
                                        review.getBooking().getTable().getRestaurant().getCnpj(),
                                        review.getBooking().getTable().getRestaurant().getFoodType(),
                                        review.getBooking().getTable().getRestaurant().getStartingHour(),
                                        review.getBooking().getTable().getRestaurant().getFinishingHour(),
                                        review.getBooking().getTable().getRestaurant().getPostalCode(),
                                        review.getBooking().getTable().getRestaurant().getAddressNumber()
                                )
                        ), new CustomerEntity(
                                review.getBooking().getCustomer().getId(),
                                review.getBooking().getCustomer().getName(),
                                review.getBooking().getCustomer().getCpf(),
                                review.getBooking().getCustomer().getEmail()
                )
                )

        );
    }


    public Review toDomain(ReviewEntity entity){
        return new Review(
                entity.getId(),
                entity.getNumberOfStars(),
                entity.getMessage(),
                entity.getDateOfReview(),
                new Booking(
                        entity.getBookingEntity().getId(),
                        entity.getBookingEntity().getStatus(),
                        entity.getBookingEntity().getBookingStart(),
                        entity.getBookingEntity().getBookingFinish(),
                        new Table(
                                entity.getBookingEntity().getTableEntity().getId(),
                                entity.getBookingEntity().getTableEntity().getTableNumber(),
                                entity.getBookingEntity().getTableEntity().getNumberOfChairs(),
                                entity.getBookingEntity().getTableEntity().getStatus(),
                                new Restaurant(
                                        entity.getBookingEntity().getTableEntity().getRestaurantEntity().getId(),
                                        entity.getBookingEntity().getTableEntity().getRestaurantEntity().getName(),
                                        entity.getBookingEntity().getTableEntity().getRestaurantEntity().getCnpj(),
                                        entity.getBookingEntity().getTableEntity().getRestaurantEntity().getFoodType(),
                                        entity.getBookingEntity().getTableEntity().getRestaurantEntity().getStartingHour(),
                                        entity.getBookingEntity().getTableEntity().getRestaurantEntity().getFinishingHour(),
                                        entity.getBookingEntity().getTableEntity().getRestaurantEntity().getPostalCode(),
                                        entity.getBookingEntity().getTableEntity().getRestaurantEntity().getAddressNumber()
                                )
                        ), new Customer(
                                entity.getBookingEntity().getCustomerEntity().getId(),
                                entity.getBookingEntity().getCustomerEntity().getName(),
                                entity.getBookingEntity().getCustomerEntity().getCpf(),
                                entity.getBookingEntity().getCustomerEntity().getEmail()
                )
                )
        );
    }

}
