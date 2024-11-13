package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.infra.persistence.CustomerEntity;

public class CustomerEntityMapper {

    public CustomerEntity toEntity(Customer customer){
        return new CustomerEntity(
                customer.getName(),
                customer.getCpf(),
                customer.getEmail()
        );
    }

    public Customer toDomain(CustomerEntity entity){
        return new Customer(
                entity.getName(),
                entity.getCpf(),
                entity.getEmail()
        );
    }

}
