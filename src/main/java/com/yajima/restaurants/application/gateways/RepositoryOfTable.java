package com.yajima.restaurants.application.gateways;

import com.yajima.restaurants.domain.entities.table.Table;

import java.util.List;
import java.util.UUID;

public interface RepositoryOfTable {

    Table createTable(Table table);

    List<Table> listEverything();

    Table findTable(UUID id);

    List<Table> listTablesPerRestaurant(UUID id);

}
