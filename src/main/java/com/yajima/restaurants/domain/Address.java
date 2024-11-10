package com.yajima.restaurants.domain;

public class Address {
    private String cep;
    private Integer number;
    private String complement;

    public Address(String cep, Integer number, String complement) {
        this.cep = cep;
        this.number = number;
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
