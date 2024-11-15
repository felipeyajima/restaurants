package com.yajima.restaurants.application.usecases;

import com.yajima.restaurants.application.gateways.RepositoryOfTable;
import com.yajima.restaurants.domain.entities.table.Table;

public class CreateTable {

    private final RepositoryOfTable repository;


    public CreateTable(RepositoryOfTable repository) {
        this.repository = repository;
    }

    public Table createTable(Table table){ return this.repository.createTable(table);}

}
