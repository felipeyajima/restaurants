package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.CreateTable;
import com.yajima.restaurants.application.usecases.ListTables;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.table.Table;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final CreateTable createTable;
    private final ListTables listTables;


    public TableController(CreateTable createTable, ListTables listTables) {
        this.createTable = createTable;
        this.listTables = listTables;
    }

    @PostMapping
    public TableDto createTable(@RequestBody TableDto dto){
        Table saved = createTable.createTable(new Table(dto.tableNumber(), dto.numberOfChairs(), dto.restaurant()));
        return new TableDto(saved.getTableNumber(), saved.getNumberOfChairs(), saved.getRestaurant());
    }

    @GetMapping
    public List<TableDto> listTables(){
        return listTables.getAllTables().stream().map(t -> new TableDto(t.getTableNumber(), t.getNumberOfChairs(), t.getRestaurant())).collect(Collectors.toList());
    }


}
