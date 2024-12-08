package com.yajima.restaurants.infra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yajima.restaurants.application.usecases.restaurant.*;
import com.yajima.restaurants.application.usecases.tables.*;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TableControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateTable createTable;
    @Mock
    private ListTables listTables;
    @Mock
    private FindTable findTable;
    @Mock
    private DeleteTable deleteTable;
    @Mock
    private ListTablesPerRestaurant listTablesPerRestaurant;
    @Mock
    private FindRestaurant findRestaurant;


    AutoCloseable mock;

    @BeforeEach
    void setup(){

        mock = MockitoAnnotations.openMocks(this);

        TableController tableController = new TableController(createTable, listTables, listTablesPerRestaurant, findTable, deleteTable, findRestaurant);
        mockMvc = MockMvcBuilders.standaloneSetup(tableController)
                .build();

    }

    @AfterEach
    void tearDown() throws  Exception{
        mock.close();
    }


    @Test
    void shouldFindTables() throws  Exception{
        List<Table> lista = new ArrayList<Table>();
        when(listTables.getAllTables()).thenReturn(lista);
        mockMvc.perform(get("/tables"))
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }


    private Restaurant generateRestaurant(){
        Restaurant restaurant = new Restaurant(UUID.randomUUID(), "Restaurant", "11.222.333/5555-66", "Chinese",  LocalTime.parse("15:32"), LocalTime.parse("15:32"), "00000-000", 12, true);
        return restaurant;
    }

    private Table generateTable(Restaurant restaurant){
        Table table = new Table(UUID.randomUUID(), 2, 4, "available", restaurant);
        return table;
    }




}
