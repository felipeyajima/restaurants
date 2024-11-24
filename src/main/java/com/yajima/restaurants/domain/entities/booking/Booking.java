package com.yajima.restaurants.domain.entities.booking;

import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.domain.entities.table.Table;
import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Booking {

    private UUID id;
    private String status;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingFinish;
    private Table table;
    private Customer customer;


    public Booking() {
    }

    public Booking(UUID id, String status, LocalDateTime bookingStart, LocalDateTime bookingFinish, Table table, Customer customer) {
        // START THE FORMATTER
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        // GET ONLY THE HOUR OF BOOKING AND TRANSFORM AGAIN TO LOCALTIME
        String startingHourBooking = bookingStart.getHour() + ":" + bookingStart.getMinute();
        LocalTime startingHourBooking1 = LocalTime.parse(startingHourBooking, formatter);

        // GET ONLY THE HOUR OF BOOKING AND TRANSFORM AGAIN TO LOCALTIME
        String finishHourBooking = bookingFinish.getHour() + ":" + bookingFinish.getMinute();
        LocalTime finishHourBooking1 = LocalTime.parse(finishHourBooking, formatter);

        // CHECK IF BOOKING START IS ON BUSINESSDAY OR NOT
        String startingHourDayOfWeek = bookingStart.getDayOfWeek().toString();
        Boolean startingHourBD = false;
        if (startingHourDayOfWeek != "SUNDAY" && startingHourDayOfWeek != "SATURDAY" ){
            startingHourBD = true;
        }
        // CHECK IF BOOKING FINISH IS ON BUSINESSDAY OR NOT
        String finishingHourDayOfWeek = bookingFinish.getDayOfWeek().toString();
        Boolean finishingHourBD = false;
        if (finishingHourDayOfWeek != "SUNDAY" && finishingHourDayOfWeek != "SATURDAY" ){
            finishingHourBD = true;
        }

        // THROW AN EXCEPTION IF USER CHOOSE A STARTING  HOUR OF  BOOKING WHEN THE RESTAURANT IS NO OPEN
        if(table.getRestaurant().getStartingHour().isAfter(startingHourBooking1)){
            throw new ControllerSystemException("restaurant is not open yet");
        }

        // THROW AN EXCEPTION IF USER CHOOSE A FINISHING  HOUR OF BOOKING WHEN THE RESTAURANT WILL BE CLOSED
        if(table.getRestaurant().getFinishingHour().isBefore(finishHourBooking1)){
            throw new ControllerSystemException("the window of booking is not available because the restaurant is closed in the requested final hour");
        }

        // THROW AN EXCEPTION IF USER CHOOSE A STARTING  HOUR OF BOOKING WHEN THE RESTAURANT DONT WORK ON WEEKENDS
        if(startingHourBD == false && table.getRestaurant().getOpenOnlyOnBusinessDay() == true){
            throw new ControllerSystemException("The starting hour of Booking isnt on BusinessDay and the Restaurant work only on BusinessDay");
        }
    // THROW AN EXCEPTION IF USER CHOOSE A FINISHING HOUR OF BOOKING WHEN THE RESTAURANT DONT WORK ON WEEKENDS
        if(finishingHourBD == false && table.getRestaurant().getOpenOnlyOnBusinessDay() == true){
            throw new ControllerSystemException("The finishing hour of Booking isnt on BusinessDay and the Restaurant work only on BusinessDay");
        }


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
