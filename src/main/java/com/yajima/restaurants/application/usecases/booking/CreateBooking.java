package com.yajima.restaurants.application.usecases.booking;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.domain.entities.booking.Booking;

public class CreateBooking {

    private final RepositoryOfBooking repository;


    public CreateBooking(RepositoryOfBooking repository) {
        this.repository = repository;
    }

    public Booking createBooking(Booking booking){
        return this.repository.createBooking(booking);
    }



}
