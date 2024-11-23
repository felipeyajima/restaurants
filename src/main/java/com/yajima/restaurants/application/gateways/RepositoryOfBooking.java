package com.yajima.restaurants.application.gateways;

import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.infra.persistence.BookingEntity;

import java.util.List;
import java.util.UUID;

public interface RepositoryOfBooking {
    Booking createBooking(Booking booking);

    List<Booking> listEverything();

    Booking findBooking(UUID id);


}
