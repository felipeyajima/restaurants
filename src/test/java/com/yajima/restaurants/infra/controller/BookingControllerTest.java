package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.booking.*;
import com.yajima.restaurants.application.usecases.customer.FindCustomer;
import com.yajima.restaurants.application.usecases.tables.FindTable;
import com.yajima.restaurants.domain.entities.booking.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CancelBooking cancelBooking;
    @Mock
    private CreateBooking createBooking;
    @Mock
    private DeleteBooking deleteBooking;
    @Mock
    FindBooking findBooking;
    @Mock
    FinishBooking finishBooking;
    @Mock
    ListBookings listBookings;
    @Mock
    WelcomeCustomer welcomeCustomer;
    @Mock
    FindTable findTable;
    @Mock
    FindCustomer findCustomer;


    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        BookingController bookingController = new BookingController(createBooking, listBookings, findBooking,deleteBooking, welcomeCustomer,cancelBooking,finishBooking, findTable, findCustomer);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception{
        mock.close();
    }


    @Test
    void shouldFindBookings() throws Exception{
        List<Booking> lista = new ArrayList<>();
        when(listBookings.getAllBookings()).thenReturn(lista);
        mockMvc.perform(get("/bookings"))
                .andExpect(status().isOk());
    }


}
