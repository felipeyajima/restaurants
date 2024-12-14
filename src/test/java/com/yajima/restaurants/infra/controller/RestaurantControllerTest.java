package com.yajima.restaurants.infra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.*;
import com.yajima.restaurants.application.usecases.restaurant.*;
import com.yajima.restaurants.application.usecases.tables.ListTablesPerRestaurant;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalTime;
import java.util.UUID;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestaurantControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateRestaurant createRestaurant;
    @Mock
    private ListRestaurants listRestaurants;
    @Mock
    private FindRestaurant findRestaurant;
    @Mock
    private DeleteRestaurant deleteRestaurant;
    @Mock
    private ListTablesPerRestaurant listTablesPerRestaurant;
    @Mock
    private ListRestaurantsPerAddress listRestaurantsPerAddress;
    @Mock
    private ListRestaurantsPerFoodType listRestaurantsPerFoodType;
    @Mock
    private ListRestaurantsPerName listRestaurantsPerName;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        RestaurantController restaurantController = new RestaurantController(createRestaurant, listRestaurants, findRestaurant, deleteRestaurant, listTablesPerRestaurant, listRestaurantsPerFoodType, listRestaurantsPerName, listRestaurantsPerAddress);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception{
        mock.close();
    }


    @Test
    void shouldAllowCreateRestaurant() throws Exception{
        //arrange
        var restaurant = generateRestaurant();
        when(createRestaurant.createRestaurant(any(Restaurant.class))).thenAnswer(r -> r.getArgument(0));

        mockMvc.perform(post("/restaurants")
                .contentType("application/json")
                .content(asJsonString(restaurant)))
                .andExpect(status().isOk());
        verify(createRestaurant, times(1)).createRestaurant(any(Restaurant.class));

    }


    public static String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }

    private Restaurant generateRestaurant(){
        Restaurant restaurant = new Restaurant(UUID.randomUUID(), "Restaurant", "11.222.333/5555-66", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32"), "00000-000", 12, true);
        return restaurant;
    }

}
