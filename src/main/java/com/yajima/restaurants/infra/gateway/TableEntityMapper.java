package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.domain.entities.table.Table;
import com.yajima.restaurants.infra.persistence.TableEntity;

public class TableEntityMapper {

    public TableEntity toEntity(Table table){
        return new TableEntity(
                table.getTableNumber(),
                table.getNumberOfChairs()
        );
    }

    public Table toDomain(TableEntity entity){
        return new Table(
                entity.getTableNumber(),
                entity.getNumberOfChairs()
        );
    }


}
