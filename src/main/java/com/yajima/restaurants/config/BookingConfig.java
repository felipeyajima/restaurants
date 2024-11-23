package com.yajima.restaurants.config;

import com.yajima.restaurants.application.gateways.RepositoryOfBooking;
import com.yajima.restaurants.application.usecases.booking.*;
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
    FindBooking findBooking(RepositoryOfBooking repositoryOfBooking){
        return new FindBooking(repositoryOfBooking);
    }

    @Bean
    WelcomeCustomer welcomeCustomer(RepositoryOfBooking repositoryOfBooking){return new WelcomeCustomer(repositoryOfBooking);}

    @Bean
    CancelBooking cancelBooking(RepositoryOfBooking repositoryOfBooking){return new CancelBooking(repositoryOfBooking);}

    @Bean
    FinishBooking finishBooking(RepositoryOfBooking repositoryOfBooking){return  new FinishBooking(repositoryOfBooking);}

    @Bean
    BookingJpaRepository bookingJpaRepository(BookingRepository repository, BookingEntityMapper mapper){
        return new BookingJpaRepository(repository, mapper);
    }


    @Bean
    BookingEntityMapper returnMapperBooking(){return new BookingEntityMapper();}


}
