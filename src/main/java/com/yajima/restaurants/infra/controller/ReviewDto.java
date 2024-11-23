package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.domain.entities.booking.Booking;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewDto {

    private UUID id;
    private Integer numberOfStars;
    private String message;
    private LocalDateTime dateOfReview;
    private Booking booking;


    public ReviewDto(UUID id, Integer numberOfStars, String message, LocalDateTime dateOfReview, Booking booking) {
        this.id = id;
        this.numberOfStars = numberOfStars;
        this.message = message;
        this.dateOfReview = dateOfReview;
        this.booking = booking;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(LocalDateTime dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
