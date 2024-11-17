package com.yajima.restaurants.config;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.application.usecases.booking.CreateBooking;
import com.yajima.restaurants.application.usecases.booking.ListBookings;
import com.yajima.restaurants.infra.gateway.BookingEntityMapper;
import com.yajima.restaurants.infra.gateway.BookingJpaRepository;
import com.yajima.restaurants.infra.persistence.BookingRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingConfig {

    @Bean
    CreateBooking createBooking(RepositoryOfBooking repositoryOfBooking) {return new CreateBooking(repositoryOfBooking);}

    @Bean
    ListBookings listBookings(RepositoryOfBooking repositoryOfBooking){return new ListBookings(repositoryOfBooking);}

    @Bean
    BookingJpaRepository bookingJpaRepository(BookingRepository repository, BookingEntityMapper mapper){
        return new BookingJpaRepository(repository, mapper);
    }


    @Bean
    BookingEntityMapper returnMapperBooking(){return new BookingEntityMapper();}


}
