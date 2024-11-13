package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.CreateCustomer;
import com.yajima.restaurants.application.usecases.ListCustomers;
import com.yajima.restaurants.domain.entities.customer.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomer createCustomer;
    private final ListCustomers listCustomers;

    public CustomerController(CreateCustomer createCustomer, ListCustomers listCustomers) {
        this.createCustomer = createCustomer;
        this.listCustomers = listCustomers;
    }


    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto dto){
        Customer saved = createCustomer.createCustomer(new Customer(dto.name(), dto.cpf(), dto.email()));
        return new CustomerDto(saved.getName(), saved.getCpf(), saved.getEmail());
    }

    @GetMapping
    public List<CustomerDto> listCustomers(){
        return listCustomers.getAllCustomers().stream().map(c -> new CustomerDto(c.getName(), c.getCpf(), c.getEmail())).collect(Collectors.toList());
    }


}
