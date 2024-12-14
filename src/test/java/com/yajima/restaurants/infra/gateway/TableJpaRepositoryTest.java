package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.persistence.RestaurantEntity;
import com.yajima.restaurants.infra.persistence.TableEntity;
import com.yajima.restaurants.infra.persistence.TableRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class TableJpaRepositoryTest {

    @Mock
    private TableRepository tableRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }

    @Test
    void shouldAllowCreateTable(){
        //arrange
        var tableEntity = generateTableEntity();
        when(tableRepository.save(tableEntity)).thenReturn(tableEntity);
        //act
        var tableAllocated = tableRepository.save(tableEntity);
        //assert
        assertThat(tableAllocated).isNotNull().isEqualTo(tableEntity);

        verify(tableRepository, times(1)).save(tableEntity);
    }

    @Test
    void shouldAllowFindTable(){
       //arrange
       var id = UUID.randomUUID();
       var tableEntity = generateTableEntity();
       tableEntity.setId(id);
       when(tableRepository.findById(any(UUID.class))).thenReturn(Optional.of(tableEntity));

       //act
       var returnedTableEntityOptional = tableRepository.findById(id);

       //assert
       assertThat(returnedTableEntityOptional).isPresent().containsSame(tableEntity);

       returnedTableEntityOptional.ifPresent(tableReceived -> {
           assertThat(tableReceived.getId()).isEqualTo(tableEntity.getId());
           assertThat(tableReceived.getTableNumber()).isEqualTo(tableEntity.getTableNumber());
       });

       verify(tableRepository, times(1)).findById(any(UUID.class));

    }

    @Test
    void shouldAllowListTables(){
        //arrange
        var table1 = generateTableEntity();
        var table2 = generateTableEntity();
        var tableList = Arrays.asList(table1, table2);

        when(tableRepository.findAll()).thenReturn(tableList);

        //act
        var tableReceived = tableRepository.findAll();

        //assert
        assertThat(tableReceived).hasSize(2).containsExactlyInAnyOrder(table1, table2);
        verify(tableRepository, times(1)).findAll();
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

        return TableEntity.builder()
                .tableNumber(12)
                .numberOfChairs(4)
                .status("available")
                .restaurantEntity(restaurantEntity)
                .build();
    }




}
