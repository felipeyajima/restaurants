package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;
import com.yajima.restaurants.infra.persistence.BookingEntity;
import com.yajima.restaurants.infra.persistence.BookingRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingJpaRepository implements RepositoryOfBooking {

    private final BookingRepository repository;
    private final BookingEntityMapper mapper;


    public BookingJpaRepository(BookingRepository repository, BookingEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Booking createBooking(Booking booking) {
        BookingEntity entity = mapper.toEntity(booking);
        entity.setStatus("reserved");
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Booking> listEverything() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Booking findBooking(UUID id) {
        BookingEntity booking = repository.findById(id).orElseThrow(()-> new ControllerSystemException("booking not found"));
        return mapper.toDomain(booking);
    }

    @Override
    public void deleteBooking(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public Booking welcomeCustomer(UUID id) {
        BookingEntity booking = repository.findById(id).orElseThrow(()-> new ControllerSystemException("booking not found"));

        if(booking.getStatus().equals("reserved")){
            booking.setStatus("activated");
            repository.save(booking);
            return mapper.toDomain(booking);
        } else {
            throw new ControllerSystemException("The status of Booking is not 'reserved'");
        }

    }

    @Override
    public Booking cancelBooking(UUID id) {
        BookingEntity booking = repository.findById(id).orElseThrow(()-> new ControllerSystemException("booking not found"));
        booking.setStatus("canceled");
        repository.save(booking);
        return mapper.toDomain(booking);
    }

    @Override
    public Booking finishBooking(UUID id) {
        BookingEntity booking = repository.findById(id).orElseThrow(()-> new ControllerSystemException("booking not found"));

        if(booking.getStatus().equals("activated")){
            booking.setStatus("finished");
            repository.save(booking);
            return mapper.toDomain(booking);
        } else {
            throw new ControllerSystemException("The status of Booking is not 'activated'");
        }
    }


}
