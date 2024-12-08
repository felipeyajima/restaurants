package com.yajima.restaurants.infra.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yajima.restaurants.application.usecases.restaurant.FindRestaurant;
import com.yajima.restaurants.application.usecases.tables.*;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void shouldCreateTable() throws  Exception{
        //arrange
        var table = generateTable();
        when(createTable.createTable(any(Table.class))).thenAnswer(t -> t.getArgument(0));

        mockMvc.perform(post("/tables")
                        .contentType("application/json")
                        .content(asJsonString(table)))
                .andExpect(status().isOk());

        verify(createTable, times(1)).createTable(any(Table.class));
    }


    public static String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(object);
    }

    private Table generateTable(){
        Restaurant restaurant1 = new Restaurant(UUID.randomUUID(), "Restaurant", "00.000.000/0000-00","chinese", LocalTime.parse("12:00"), LocalTime.parse("18:00"), "00000-000", 2, true);
        Table table = new Table(UUID.randomUUID(), 2, 4, "available", restaurant1);
        return table;
    }


}
