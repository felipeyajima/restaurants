package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.application.gateways.RepositoryOfCustomer;
import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.infra.persistence.CustomerEntity;
import com.yajima.restaurants.infra.persistence.CustomerRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomerJpaRepository implements RepositoryOfCustomer {

    private final CustomerRepository repository;

    private final CustomerEntityMapper mapper;

    public CustomerJpaRepository(CustomerRepository repository, CustomerEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Customer> listEverything() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Customer findCustomer(UUID id) {
        CustomerEntity customer = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("customer not found"));
        return mapper.toDomain(customer);
    }


}
