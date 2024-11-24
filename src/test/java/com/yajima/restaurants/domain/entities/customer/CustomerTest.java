package com.yajima.restaurants.domain.entities.customer;

import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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



}
