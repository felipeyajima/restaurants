package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.booking.CreateBooking;
import com.yajima.restaurants.application.usecases.booking.FindBooking;
import com.yajima.restaurants.application.usecases.booking.ListBookings;
import com.yajima.restaurants.domain.entities.booking.Booking;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final CreateBooking createBooking;
    private final ListBookings listBookings;
    private final FindBooking findBooking;


    public BookingController(CreateBooking createBooking, ListBookings listBookings, FindBooking findBooking) {
        this.createBooking = createBooking;
        this.listBookings = listBookings;
        this.findBooking = findBooking;
    }
    @PostMapping
    public BookingDto createBooking(@RequestBody BookingDto dto){
        Booking saved = createBooking.createBooking(new Booking(
                dto.getId(),
                dto.getStatus(),
                dto.getBookingStart(),
                dto.getBookingFinish(),
                dto.getTable(),
                dto.getCustomer()

        ));
        return new BookingDto(saved.getId(), saved.getStatus(), saved.getBookingStart(), saved.getBookingFinish(), saved.getTable(), saved.getCustomer());
    }

    @GetMapping
    public List<BookingDto> listBookings(){
        return listBookings.getAllBookings().stream().map(b -> new BookingDto(
                b.getId(), b.getStatus(), b.getBookingStart(), b.getBookingFinish(), b.getTable(), b.getCustomer()
        )).collect(Collectors.toList());
    }

    /*
    @PutMapping("{id}/welcome-customer")
    public BookingDto validate(@PathVariable UUID id){

    }

     */

    @GetMapping("/{id}")
    public BookingDto findBooking(@PathVariable UUID id){
        Booking booking = findBooking.findBooking(id);
        return new BookingDto(booking.getId(), booking.getStatus(), booking.getBookingStart(), booking.getBookingFinish(), booking.getTable(), booking.getCustomer());
    }


}
