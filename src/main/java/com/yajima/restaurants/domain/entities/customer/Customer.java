package com.yajima.restaurants.domain.entities.customer;

public class Customer {
    private String name;
    private String cpf;
    private String email;

    public String getName() {
        return name;
    }


    public Customer(String name, String cpf, String email){
        this.name = name;
        this.email = email;

        if(cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")){
            throw new IllegalArgumentException("invalid cpf!");
        }
        this.cpf = cpf;
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
