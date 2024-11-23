package com.yajima.restaurants.application.usecases.booking;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.domain.entities.booking.Booking;

import java.util.UUID;

public class CancelBooking {

    private final RepositoryOfBooking repository;


    public CancelBooking(RepositoryOfBooking repository) {
        this.repository = repository;
    }

    public Booking cancelBooking(UUID id){ return  this.repository.cancelBooking(id);}
}
