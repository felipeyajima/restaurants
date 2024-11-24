package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.booking.*;
import com.yajima.restaurants.application.usecases.customer.FindCustomer;
import com.yajima.restaurants.application.usecases.tables.FindTable;
import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
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

    private final DeleteBooking deleteBooking;

    private final WelcomeCustomer welcomeCustomer;

    private final CancelBooking cancelBooking;
    private final FinishBooking finishBooking;

    private final FindTable findTable;
    private final FindCustomer findCustomer;


    public BookingController(CreateBooking createBooking, ListBookings listBookings, FindBooking findBooking, DeleteBooking deleteBooking, WelcomeCustomer welcomeCustomer, CancelBooking cancelBooking, FinishBooking finishBooking, FindTable findTable, FindCustomer findCustomer) {
        this.createBooking = createBooking;
        this.listBookings = listBookings;
        this.findBooking = findBooking;
        this.deleteBooking = deleteBooking;
        this.welcomeCustomer = welcomeCustomer;
        this.cancelBooking = cancelBooking;
        this.finishBooking = finishBooking;
        this.findTable = findTable;
        this.findCustomer = findCustomer;
    }
    @PostMapping
    public BookingDto createBooking(@RequestBody BookingDto dto){

        Table table = findTable.findTable(dto.getTable().getId());
        Customer customer = findCustomer.findCustomer(dto.getCustomer().getId());

        Booking saved = createBooking.createBooking(new Booking(
                dto.getId(),
                dto.getStatus(),
                dto.getBookingStart(),
                dto.getBookingFinish(),
                new Table(
                        table.getId(),
                        table.getTableNumber(),
                        table.getNumberOfChairs(),
                        table.getStatus(),
                        new Restaurant(
                                table.getRestaurant().getId(),
                                table.getRestaurant().getName(),
                                table.getRestaurant().getCnpj(),
                                table.getRestaurant().getFoodType(),
                                table.getRestaurant().getStartingHour(),
                                table.getRestaurant().getFinishingHour(),
                                table.getRestaurant().getPostalCode(),
                                table.getRestaurant().getAddressNumber(),
                                table.getRestaurant().getOpenOnlyOnBusinessDay()
                        )
                ),
                new Customer(
                    customer.getId(),
                    customer.getName(),
                    customer.getCpf(),
                    customer.getEmail()
                )

        ));
        return new BookingDto(saved.getId(), saved.getStatus(), saved.getBookingStart(), saved.getBookingFinish(), saved.getTable(), saved.getCustomer());
    }

    @GetMapping
    public List<BookingDto> listBookings(){
        return listBookings.getAllBookings().stream().map(b -> new BookingDto(
                b.getId(), b.getStatus(), b.getBookingStart(), b.getBookingFinish(), b.getTable(), b.getCustomer()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookingDto findBooking(@PathVariable UUID id){
        Booking booking = findBooking.findBooking(id);
        return new BookingDto(booking.getId(), booking.getStatus(), booking.getBookingStart(), booking.getBookingFinish(), booking.getTable(), booking.getCustomer());
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable UUID id){
        deleteBooking.deleteBooking(id);
    }

    @PutMapping("/{id}/welcomeCustomer")
    public BookingDto welcomeCustomer(@PathVariable UUID id){
        Booking booking = welcomeCustomer.welcomeCustomer(id);
        return new BookingDto(booking.getId(), booking.getStatus(), booking.getBookingStart(), booking.getBookingFinish(), booking.getTable(), booking.getCustomer());
    }

    @PutMapping("/{id}/cancelBooking")
    public BookingDto cancelBooking(@PathVariable UUID id){
        Booking booking = cancelBooking.cancelBooking(id);
        return new BookingDto(booking.getId(), booking.getStatus(), booking.getBookingStart(), booking.getBookingFinish(), booking.getTable(), booking.getCustomer());
    }


    @PutMapping("/{id}/finishBooking")
    public BookingDto finishBooking(@PathVariable UUID id){
        Booking booking = finishBooking.finishBooking(id);
        return new BookingDto(booking.getId(), booking.getStatus(), booking.getBookingStart(), booking.getBookingFinish(), booking.getTable(), booking.getCustomer());
    }


}
