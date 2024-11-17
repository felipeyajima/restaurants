package com.yajima.restaurants.application.usecases.tables;

import com.yajima.restaurants.application.gateways.RepositoryOfTable;
import com.yajima.restaurants.domain.entities.table.Table;

import java.util.List;
import java.util.UUID;

public class ListTablesPerRestaurant {

    private final RepositoryOfTable repository;


    public ListTablesPerRestaurant(RepositoryOfTable repository) {
        this.repository = repository;
    }

    public List<Table> listTablesPerRestaurant(UUID id){
        return this.repository.listTablesPerRestaurant(id);
    }

}
