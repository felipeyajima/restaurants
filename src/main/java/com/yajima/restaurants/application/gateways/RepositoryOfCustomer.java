package com.yajima.restaurants.application.gateways;

import com.yajima.restaurants.domain.entities.customer.Customer;

import java.util.List;
import java.util.UUID;

public interface RepositoryOfCustomer {

    Customer createCustomer(Customer customer);

    List<Customer> listEverything();

    Customer findCustomer(UUID id);

    void deleteCustomer(UUID id);

}
