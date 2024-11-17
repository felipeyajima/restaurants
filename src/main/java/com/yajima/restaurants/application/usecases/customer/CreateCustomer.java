package com.yajima.restaurants.application.usecases.customer;

import com.yajima.restaurants.application.gateways.RepositoryOfCustomer;
import com.yajima.restaurants.domain.entities.customer.Customer;

public class CreateCustomer {

    private final RepositoryOfCustomer repository;

    public CreateCustomer(RepositoryOfCustomer repository) {
        this.repository = repository;
    }

    public Customer createCustomer(Customer customer){
        return repository.createCustomer(customer);
    }

}
