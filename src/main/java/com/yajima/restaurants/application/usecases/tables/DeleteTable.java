package com.yajima.restaurants.application.usecases.tables;

import com.yajima.restaurants.application.gateways.RepositoryOfTable;

import java.util.UUID;

public class DeleteTable {

    public final RepositoryOfTable repository;


    public DeleteTable(RepositoryOfTable repository) {
        this.repository = repository;
    }

    public void deleteTable(UUID id){this.repository.deleteTable(id);}
}
