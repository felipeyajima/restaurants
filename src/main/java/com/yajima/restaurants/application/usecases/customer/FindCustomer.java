package com.yajima.restaurants.application.usecases.customer;

import com.yajima.restaurants.application.gateways.RepositoryOfCustomer;
import com.yajima.restaurants.domain.entities.customer.Customer;

import java.util.UUID;

public class FindCustomer {
    public final RepositoryOfCustomer repository;


    public FindCustomer(RepositoryOfCustomer repository) {
        this.repository = repository;
    }

    public Customer findCustomer(UUID id){
        return this.repository.findCustomer(id);
    }

}
