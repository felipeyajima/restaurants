package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.tables.CreateTable;
import com.yajima.restaurants.application.usecases.tables.FindTable;
import com.yajima.restaurants.application.usecases.tables.ListTables;
import com.yajima.restaurants.application.usecases.tables.ListTablesPerRestaurant;
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

    private final FindTable findTable;


    public TableController(CreateTable createTable, ListTables listTables, ListTablesPerRestaurant listTablesPerRestaurant, FindTable findTable) {
        this.createTable = createTable;
        this.listTables = listTables;
        this.findTable = findTable;
    }

    @PostMapping
    public TableDto createTable(@RequestBody TableDto dto){
        Table saved = createTable.createTable(new Table(
                dto.getId(),
                dto.getTableNumber(),
                dto.getNumberOfChairs(),
                dto.getStatus(),
                dto.getRestaurant()

                ));
        return new TableDto(saved.getId(), saved.getTableNumber(), saved.getNumberOfChairs(), saved.getStatus(), saved.getRestaurant());
    }

    @GetMapping
    public List<TableDto> listTables(){
        return listTables.getAllTables().stream().map(t -> new TableDto(
                t.getId(), t.getTableNumber(), t.getNumberOfChairs(), t.getStatus(), t.getRestaurant()
                )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TableDto findTable(@PathVariable UUID id){
        Table table = findTable.findTable(id);
        return new TableDto(table.getId(), table.getTableNumber() ,table.getNumberOfChairs() ,table.getStatus(), table.getRestaurant());
    }

}
