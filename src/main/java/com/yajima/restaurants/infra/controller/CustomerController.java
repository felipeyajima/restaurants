package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.customer.CreateCustomer;
import com.yajima.restaurants.application.usecases.customer.DeleteCustomer;
import com.yajima.restaurants.application.usecases.customer.FindCustomer;
import com.yajima.restaurants.application.usecases.customer.ListCustomers;
import com.yajima.restaurants.domain.entities.customer.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomer createCustomer;
    private final ListCustomers listCustomers;
    private final FindCustomer findCustomer;
    private final DeleteCustomer deleteCustomer;

    public CustomerController(CreateCustomer createCustomer, ListCustomers listCustomers, FindCustomer findCustomer, DeleteCustomer deleteCustomer) {
        this.createCustomer = createCustomer;
        this.listCustomers = listCustomers;
        this.findCustomer = findCustomer;
        this.deleteCustomer = deleteCustomer;
    }


    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto dto){
        Customer saved = createCustomer.createCustomer(new Customer(dto.getId(), dto.getName(), dto.getCpf(), dto.getEmail()));
        return new CustomerDto(saved.getId(), saved.getName(), saved.getCpf(), saved.getEmail());
    }

    @GetMapping
    public List<CustomerDto> listCustomers(){
        return listCustomers.getAllCustomers().stream().map(c -> new CustomerDto(c.getId(), c.getName(), c.getCpf(), c.getEmail())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerDto findCustomer(@PathVariable UUID id){
        Customer customer = findCustomer.findCustomer(id);
        return new CustomerDto(customer.getId(), customer.getName(), customer.getCpf(), customer.getEmail());
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable UUID id){
        deleteCustomer.deleteCustomer(id);
    }
}
