package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.infra.controller.exceptions.ControllerNotFoundException;
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
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Booking> listEverything() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Booking findBooking(UUID id) {
        BookingEntity booking = repository.findById(id).orElseThrow(()-> new ControllerNotFoundException("booking not found"));
        return mapper.toDomain(booking);
    }

    @Override
    public void deleteBooking(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public Booking welcomeCustomer(UUID id) {
        BookingEntity booking = repository.findById(id).orElseThrow(()-> new ControllerNotFoundException("booking not found"));
        booking.setStatus("ativated");
        repository.save(booking);
        return mapper.toDomain(booking);
    }

    @Override
    public Booking cancelBooking(UUID id) {
        BookingEntity booking = repository.findById(id).orElseThrow(()-> new ControllerNotFoundException("booking not found"));
        booking.setStatus("canceled");
        repository.save(booking);
        return mapper.toDomain(booking);
    }

    @Override
    public Booking finishBooking(UUID id) {
        BookingEntity booking = repository.findById(id).orElseThrow(()-> new ControllerNotFoundException("booking not found"));
        booking.setStatus("finished");
        repository.save(booking);
        return mapper.toDomain(booking);
    }


}
