package com.yajima.restaurants.application.gateways;

import com.yajima.restaurants.domain.entities.customer.Customer;

import java.util.List;

public interface RepositoryOfCustomer {

    Customer createCustomer(Customer customer);

    List<Customer> listEverything();

}
