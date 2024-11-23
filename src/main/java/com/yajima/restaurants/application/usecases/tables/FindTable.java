package com.yajima.restaurants.application.usecases.tables;

import com.yajima.restaurants.application.gateways.RepositoryOfTable;
import com.yajima.restaurants.domain.entities.table.Table;

import java.util.UUID;

public class FindTable {

    public final RepositoryOfTable repository;


    public FindTable(RepositoryOfTable repository) {
        this.repository = repository;
    }

    public Table findTable(UUID id){
        return this.repository.findTable(id);
    }

}
