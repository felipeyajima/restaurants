package com.yajima.restaurants.application.usecases.booking;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.domain.entities.booking.Booking;

import java.util.UUID;

public class FindBooking {

    private final RepositoryOfBooking repository;


    public FindBooking(RepositoryOfBooking repository) {
        this.repository = repository;
    }

    public Booking findBooking(UUID id) {
        return this.repository.findBooking(id);
    }

}
