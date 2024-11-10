package com.yajima.restaurants.domain.entities.restaurant;

import com.yajima.restaurants.domain.Address;

import java.time.LocalTime;

public class RestaurantBuilder {
    private Restaurant restaurant;

    public Restaurant withNameCnpjOpenningHourFinishingHour(String name, String cnpj, LocalTime openingHour, LocalTime finishingHour){
        this.restaurant = new Restaurant(name, cnpj, "", openingHour, finishingHour);
        return this.restaurant;
    }

    public Restaurant includeAddress(String cep, Integer number, String complement){
        this.restaurant.setAddress(new Address(cep, number, complement));
        return this.restaurant;
    }
}
