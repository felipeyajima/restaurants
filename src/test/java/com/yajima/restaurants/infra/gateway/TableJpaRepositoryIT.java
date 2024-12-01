package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.persistence.RestaurantEntity;
import com.yajima.restaurants.infra.persistence.RestaurantRepository;
import com.yajima.restaurants.infra.persistence.TableEntity;
import com.yajima.restaurants.infra.persistence.TableRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class TableJpaRepositoryIT {

    // testes de integração

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void shouldAllowCreateDatabaseTable(){
        var totalOfRecords = tableRepository.count();
        assertThat(totalOfRecords).isNotNegative();
    }

    @Test
    void shouldAllowCreateTable(){
        //arrange
        var tableEntity = generateTableEntity();
        //act
        var receivedTable = tableRepository.save(tableEntity);
        //assert
        assertThat(receivedTable)
                .isInstanceOf(TableEntity.class)
                .isNotNull();
        assertThat(receivedTable.getTableNumber()).isEqualTo(tableEntity.getTableNumber());
    }


    @Test
    void shouldAllowFindTable(){
        //arrange
        var tableEntity = generateTableEntity();
        TableEntity savedTableEntity = saveTable(tableEntity);
        //act
        var receivedTableEntityOptional = tableRepository.findById(savedTableEntity.getId());

        //assert
        assertThat(receivedTableEntityOptional).isPresent();
        receivedTableEntityOptional.ifPresent(receivedTableEntity -> {
            assertThat(receivedTableEntity.getTableNumber()).isEqualTo(savedTableEntity.getTableNumber());
        });
    }


    @Test
    void shouldAllowListTables(){
        var tableEntity1 = generateTableEntity();
        TableEntity savedTableEntity1 = saveTable(tableEntity1);

        var tableEntity2 = generateTableEntity();
        TableEntity savedTableEntity2 = saveTable(tableEntity2);

        var tablesList = tableRepository.findAll();
        assertThat(tablesList).hasSizeGreaterThan(0);
    }




    private TableEntity generateTableEntity(){

        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .cnpj("00.000.000/0000-00")
                .name("Brazilian Restaurant")
                .foodType("Northeast Food")
                .startingHour(LocalTime.parse("12:00"))
                .finishingHour(LocalTime.parse("15:00"))
                .postalCode("000000-000")
                .addressNumber(2)
                .openOnlyOnBusinessDay(true)
                .build();

        RestaurantEntity savedRestaurant = restaurantRepository.save(restaurantEntity);


        return TableEntity.builder()
                .tableNumber(2)
                .numberOfChairs(2)
                .status("available")
                .restaurantEntity(savedRestaurant)
                .build();
    }

    private TableEntity saveTable(TableEntity tableEntity){
        return tableRepository.save(tableEntity);
    }



}
