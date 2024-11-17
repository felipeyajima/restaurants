package com.yajima.restaurants.application.usecases.booking;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.domain.entities.booking.Booking;

import java.util.List;

public class ListBookings {

    private final RepositoryOfBooking repository;


    public ListBookings(RepositoryOfBooking repository) {
        this.repository = repository;
    }

    public List<Booking> getAllBookings(){
        return this.repository.listEverything();
    }
}
