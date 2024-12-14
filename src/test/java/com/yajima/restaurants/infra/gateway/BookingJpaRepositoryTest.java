package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.controller.exceptions.ControllerSystemException;
import com.yajima.restaurants.infra.persistence.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingJpaRepositoryTest {

    @Mock
    private BookingRepository bookingRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }


    @Test
    void shouldAllowCreateBooking(){
        //arrange
        var bookingEntity = generateBookingEntity();
        when(bookingRepository.save(bookingEntity)).thenReturn(bookingEntity);
        //act
        var bookingAllocated = bookingRepository.save(bookingEntity);
        //assert
        assertThat(bookingAllocated).isNotNull().isEqualTo(bookingEntity);
        verify(bookingRepository, times(1)).save(bookingEntity);
    }


    @Test
    void shouldAllowFindBooking(){
        //arrange
        var id = UUID.randomUUID();
        var bookingEntity = generateBookingEntity();
        bookingEntity.setId(id);
        when(bookingRepository.findById(any(UUID.class))).thenReturn(Optional.of(bookingEntity));

        //act
        var returnedBookingEntityOptional = bookingRepository.findById(id);

        //assert
        assertThat(returnedBookingEntityOptional).isPresent().containsSame(bookingEntity);

        returnedBookingEntityOptional.ifPresent(bookingReceived -> {
            assertThat(bookingReceived.getId()).isEqualTo(bookingEntity.getId());

        });

        verify(bookingRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void shouldAllowListBookings(){
        //arrange
        var booking1 = generateBookingEntity();
        var booking2 = generateBookingEntity();
        var bookingList = Arrays.asList(booking1, booking2);

        when(bookingRepository.findAll()).thenReturn(bookingList);

        //act
        var bookingReceived = bookingRepository.findAll();

        //assert
        assertThat(bookingReceived).hasSize(2).containsExactlyInAnyOrder(booking1, booking2);
        verify(bookingRepository, times(1)).findAll();
    }



    private BookingEntity generateBookingEntity(){

        CustomerEntity customerEntity = CustomerEntity.builder()
                .cpf("333.333.333-00")
                .email("john.silva@gmail.com")
                .name("John Silva")
                .build();

        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .cnpj("00.000.000/0000-00")
                .name("Brazilian Restaurant")
                .foodType("Northeast Food")
                .startingHour(LocalTime.parse("12:00"))
                .finishingHour(LocalTime.parse("15:00"))
                .postalCode("000000-000")
                .addressNumber(2)
                .openOnlyOnBusinessDay(false)
                .build();

        TableEntity tableEntity = TableEntity.builder()
                .status("available")
                .numberOfChairs(4)
                .tableNumber(1)
                .restaurantEntity(restaurantEntity)
                .build();

        return BookingEntity.builder()
                .bookingStart(LocalDateTime.parse("2024-11-22T12:15:30"))
                .bookingFinish(LocalDateTime.parse("2024-11-22T13:15:30"))
                .customerEntity(customerEntity)
                .tableEntity(tableEntity)
                .build();
    }

}
