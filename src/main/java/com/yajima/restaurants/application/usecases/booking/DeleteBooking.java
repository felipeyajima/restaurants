package com.yajima.restaurants.application.usecases.booking;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;

import java.util.UUID;

public class DeleteBooking {

    private final RepositoryOfBooking repository;


    public DeleteBooking(RepositoryOfBooking repository) {
        this.repository = repository;
    }

    public void deleteBooking(UUID id){this.repository.deleteBooking(id);}
}
