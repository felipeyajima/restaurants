package com.yajima.restaurants.application.usecases.customer;

import com.yajima.restaurants.application.gateways.RepositoryOfCustomer;

import java.util.UUID;

public class DeleteCustomer {
    public final RepositoryOfCustomer repository;


    public DeleteCustomer(RepositoryOfCustomer repository) {
        this.repository = repository;
    }

    public void deleteCustomer(UUID id){this.repository.deleteCustomer(id);}
}
