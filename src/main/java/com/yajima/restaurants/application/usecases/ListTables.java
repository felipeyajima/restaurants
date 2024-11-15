package com.yajima.restaurants.application.usecases;

import com.yajima.restaurants.application.gateways.RepositoryOfTable;
import com.yajima.restaurants.domain.entities.table.Table;

import java.util.List;

public class ListTables {

    private final RepositoryOfTable repository;


    public ListTables(RepositoryOfTable repository) {
        this.repository = repository;
    }

    public List<Table> getAllTables(){
        return this.repository.listEverything();
    }

}
