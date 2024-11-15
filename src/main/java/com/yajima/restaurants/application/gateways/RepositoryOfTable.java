package com.yajima.restaurants.application.gateways;

import com.yajima.restaurants.domain.entities.table.Table;

import java.util.List;

public interface RepositoryOfTable {

    Table createTable(Table table);

    List<Table> listEverything();

}
