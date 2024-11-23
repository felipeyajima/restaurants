package com.yajima.restaurants.application.usecases.booking;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.domain.entities.booking.Booking;

import java.util.UUID;


public class FinishBooking {

    private final RepositoryOfBooking repository;


    public FinishBooking(RepositoryOfBooking repository) {
        this.repository = repository;
    }


    public Booking finishBooking(UUID id){ return  this.repository.finishBooking(id);}
}
