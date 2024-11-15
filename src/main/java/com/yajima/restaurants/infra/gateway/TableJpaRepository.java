package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.application.gateways.RepositoryOfTable;
import com.yajima.restaurants.domain.entities.table.Table;
import com.yajima.restaurants.infra.persistence.TableEntity;
import com.yajima.restaurants.infra.persistence.TableRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TableJpaRepository implements RepositoryOfTable {

    private final TableRepository repository;
    private final TableEntityMapper mapper;

    public TableJpaRepository(TableRepository repository, TableEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Table createTable(Table table) {
        TableEntity entity = mapper.toEntity(table);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Table> listEverything() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());

    }
}
