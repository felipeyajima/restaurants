package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.domain.entities.table.Table;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingDto {
    private UUID id;
    private String status;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingFinish;
    private Table table;
    private Customer customer;

    public BookingDto(UUID id, String status, LocalDateTime bookingStart, LocalDateTime bookingFinish, Table table, Customer customer) {
        this.id = id;
        this.status = status;
        this.bookingStart = bookingStart;
        this.bookingFinish = bookingFinish;
        this.table = table;
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getBookingStart() {
        return bookingStart;
    }

    public void setBookingStart(LocalDateTime bookingStart) {
        this.bookingStart = bookingStart;
    }

    public LocalDateTime getBookingFinish() {
        return bookingFinish;
    }

    public void setBookingFinish(LocalDateTime bookingFinish) {
        this.bookingFinish = bookingFinish;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
