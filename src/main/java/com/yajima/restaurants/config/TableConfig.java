package com.yajima.restaurants.config;

import com.yajima.restaurants.application.gateways.RepositoryOfTable;
import com.yajima.restaurants.application.usecases.CreateTable;
import com.yajima.restaurants.application.usecases.ListTables;
import com.yajima.restaurants.application.usecases.ListTablesPerRestaurant;
import com.yajima.restaurants.infra.gateway.TableEntityMapper;
import com.yajima.restaurants.infra.gateway.TableJpaRepository;
import com.yajima.restaurants.infra.persistence.TableRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TableConfig {

    @Bean
    CreateTable createTable(RepositoryOfTable repositoryOfTable){
        return new CreateTable(repositoryOfTable);
    }

    @Bean
    ListTables listTables(RepositoryOfTable repositoryOfTable){
        return new ListTables(repositoryOfTable);
    }

    @Bean
    ListTablesPerRestaurant listTablesPerRestaurant(RepositoryOfTable repositoryOfTable){ return  new ListTablesPerRestaurant(repositoryOfTable);}

    @Bean
    TableJpaRepository tableJpaRepository(TableRepository repository, TableEntityMapper mapper){
        return new TableJpaRepository(repository, mapper);
    }

    @Bean
    TableEntityMapper returnMapperTable(){
        return new TableEntityMapper();
    }


}
