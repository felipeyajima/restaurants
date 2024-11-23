package com.yajima.restaurants.application.usecases.booking;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.domain.entities.booking.Booking;

import java.util.UUID;

public class WelcomeCustomer {

    private final RepositoryOfBooking repository;


    public WelcomeCustomer(RepositoryOfBooking repository) {
        this.repository = repository;
    }

    public Booking welcomeCustomer(UUID id){
        return this.repository.welcomeCustomer(id);
    }
}
