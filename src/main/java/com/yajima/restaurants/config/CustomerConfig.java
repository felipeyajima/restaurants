package com.yajima.restaurants.config;

import com.yajima.restaurants.application.gateways.RepositoryOfCustomer;
import com.yajima.restaurants.application.usecases.customer.CreateCustomer;
import com.yajima.restaurants.application.usecases.customer.ListCustomers;
import com.yajima.restaurants.infra.gateway.CustomerEntityMapper;
import com.yajima.restaurants.infra.gateway.CustomerJpaRepository;

import com.yajima.restaurants.infra.persistence.CustomerRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CreateCustomer createCustomer(RepositoryOfCustomer repositoryOfCustomer){
        return new CreateCustomer(repositoryOfCustomer);
    }

    @Bean
    ListCustomers listCustomers(RepositoryOfCustomer repositoryOfCustomer){
        return new ListCustomers(repositoryOfCustomer);
    }

    @Bean
    CustomerJpaRepository customerJpaRepository(CustomerRepository repository, CustomerEntityMapper mapper){
        return new CustomerJpaRepository(repository, mapper);
    }

    @Bean
    CustomerEntityMapper returnMapperCustomer(){
        return new CustomerEntityMapper();
    }

}
