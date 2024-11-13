package com.yajima.restaurants.infra.controller;

public record CustomerDto(
        String name,
        String cpf,
        String email
) {

}
