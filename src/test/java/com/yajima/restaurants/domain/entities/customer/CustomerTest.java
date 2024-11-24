package com.yajima.restaurants.domain.entities.customer;

import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class CustomerTest {

    @Test
    public void shouldntSaveEmailWithInvalidFormat(){
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Customer(UUID.randomUUID(), "John", "000.000.000-00", "john.com"));
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Customer(UUID.randomUUID(), "John", "000.000.000-00", ""));
    }

    @Test
    public void shouldntSaveCpfWithInvalidFormat(){
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Customer(UUID.randomUUID(), "John", "00000000000", "john@teste.com"));
        Assertions.assertThrows(ControllerSystemException.class,
                () -> new Customer(UUID.randomUUID(), "John", "", "john@teste.com"));
    }


    @Test
    void shouldRegisterCustomer(){
        Customer customer1 = new Customer(UUID.randomUUID(), "John", "333.333.333-00", "teste@teste.com.br");

        Assertions.assertEquals(Customer.class, customer1.getClass());
        Assertions.assertEquals("John", customer1.getName());
        Assertions.assertEquals("333.333.333-00", customer1.getCpf());
        Assertions.assertEquals("teste@teste.com.br", customer1.getEmail());

    }



}
