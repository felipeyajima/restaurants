package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.CreateTable;
import com.yajima.restaurants.application.usecases.ListTables;
import com.yajima.restaurants.application.usecases.ListTablesPerRestaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final CreateTable createTable;
    private final ListTables listTables;

    private final ListTablesPerRestaurant listTablesPerRestaurant;


    public TableController(CreateTable createTable, ListTables listTables, ListTablesPerRestaurant listTablesPerRestaurant) {
        this.createTable = createTable;
        this.listTables = listTables;
        this.listTablesPerRestaurant = listTablesPerRestaurant;
    }

    @PostMapping
    public TableDto createTable(@RequestBody TableDto dto){
        Table saved = createTable.createTable(new Table(
                dto.getId(),
                dto.getTableNumber(),
                dto.getNumberOfChairs(),
                dto.getRestaurant()
                ));
        return dto;
    }

    @GetMapping
    public List<TableDto> listTables(){
        return listTables.getAllTables().stream().map(t -> new TableDto(
                t.getId(), t.getTableNumber(), t.getNumberOfChairs(), t.getRestaurant()
                )).collect(Collectors.toList());
    }

    @GetMapping("/restaurantId/{id}/")
    public List<TableDto> ListTablesPerRestaurant(@PathVariable UUID id){
        return listTablesPerRestaurant.listTablesPerRestaurant(id).stream().map(t -> new TableDto(
                t.getId(), t.getTableNumber(), t.getNumberOfChairs(), t.getRestaurant()
        )).collect(Collectors.toList());
    }

}
