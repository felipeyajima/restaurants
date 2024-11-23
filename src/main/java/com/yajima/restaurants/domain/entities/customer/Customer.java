package com.yajima.restaurants.domain.entities.customer;

import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;

import java.util.UUID;

public class Customer {
    private UUID id;
    private String name;
    private String cpf;
    private String email;


    public Customer() {
    }

    public Customer(UUID id, String name, String cpf, String email){
        this.id = id;
        this.name = name;
        this.email = email;

        if(cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")){
            throw new ControllerSystemException("invalid cpf!");
        }

        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
