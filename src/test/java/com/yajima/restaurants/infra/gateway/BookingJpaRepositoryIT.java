package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.persistence.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class BookingJpaRepositoryIT {

    // testes de integração

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableRepository tableRepository;


    @Test
    void shouldAllowCreateDatabaseTable(){
        var totalOfRecords = bookingRepository.count();
        assertThat(totalOfRecords).isNotNegative();
    }


    @Test
    void shouldAllowCreateBooking(){
        //arrange
        var bookingEntity = generateBookingEntity();
        //act
        var receivedBooking = bookingRepository.save(bookingEntity);
        //assert
        assertThat(receivedBooking)
                .isInstanceOf(BookingEntity.class)
                .isNotNull();
        assertThat(receivedBooking.getTableEntity()).isEqualTo(bookingEntity.getTableEntity());
    }


    @Test
    void shouldAllowFindBooking(){
        //arrange
        var bookingEntity = generateBookingEntity();
        BookingEntity savedBookingEntity = saveBooking(bookingEntity);
        //act
        var receivedBookingEntityOptional = bookingRepository.findById(savedBookingEntity.getId());

        //assert
        assertThat(receivedBookingEntityOptional).isPresent();
        receivedBookingEntityOptional.ifPresent(receivedBookingEntity -> {
            assertThat(receivedBookingEntity.getStatus()).isEqualTo(savedBookingEntity.getStatus());
        });
    }

    @Test
    void shouldAllowListBookings(){
        var bookingEntity1 = generateBookingEntity();
        BookingEntity savedBookingEntity1 = saveBooking(bookingEntity1);

        var bookingEntity2 = generateBookingEntity();
        BookingEntity savedBookingEntity2 = saveBooking(bookingEntity2);

        var bookingsList = bookingRepository.findAll();
        assertThat(bookingsList).hasSizeGreaterThan(0);
    }


    private BookingEntity generateBookingEntity(){
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .cnpj("00.000.000/0000-00")
                .name("Brazilian Restaurant")
                .foodType("Northeast Food")
                .startingHour(LocalTime.parse("12:00"))
                .finishingHour(LocalTime.parse("15:00"))
                .postalCode("000000-000")
                .addressNumber(2)
                .openOnlyOnBusinessDay(true)
                .build();

        RestaurantEntity savedRestaurant = restaurantRepository.save(restaurantEntity);


        TableEntity tableEntity = TableEntity.builder()
                .tableNumber(2)
                .numberOfChairs(2)
                .status("available")
                .restaurantEntity(savedRestaurant)
                .build();

        TableEntity savedTable = tableRepository.save(tableEntity);

        CustomerEntity customerEntity = CustomerEntity.builder()
                .name("Joseph")
                .cpf("222.222.222-00")
                .email("joseph@gmail.com")
                .build();

        CustomerEntity savedcustomer = customerRepository.save(customerEntity);

        return BookingEntity.builder()
                .bookingStart(LocalDateTime.parse("2024-11-22T12:15:30"))
                .bookingFinish(LocalDateTime.parse("2024-11-22T13:15:30"))
                .tableEntity(tableEntity)
                .customerEntity(customerEntity)
                .build();

    }

    private BookingEntity saveBooking(BookingEntity bookingEntity){
        return bookingRepository.save(bookingEntity);
    }


}
