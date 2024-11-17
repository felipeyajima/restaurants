package com.yajima.restaurants.application.usecases.customer;

import com.yajima.restaurants.application.gateways.RepositoryOfCustomer;
import com.yajima.restaurants.domain.entities.customer.Customer;

import java.util.List;

public class ListCustomers {

    private final RepositoryOfCustomer repository;

    public ListCustomers(RepositoryOfCustomer repository) {
        this.repository = repository;
    }

    public List<Customer> getAllCustomers(){
        return this.repository.listEverything();
    }
}
