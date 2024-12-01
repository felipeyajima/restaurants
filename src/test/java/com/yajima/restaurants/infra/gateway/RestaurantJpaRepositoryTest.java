package com.yajima.restaurants.infra.gateway;


import com.yajima.restaurants.infra.persistence.RestaurantEntity;
import com.yajima.restaurants.infra.persistence.RestaurantRepository;
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


public class RestaurantJpaRepositoryTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }
    @Test
    void shouldAllowCreateRestaurant(){
        // Arrange
        var restaurantEntity = generateRestaurantEntity();
        when(restaurantRepository.save(restaurantEntity)).thenReturn(restaurantEntity);
        // act
        var restaurantAllocated = restaurantRepository.save(restaurantEntity);
        // assert - fazer as validações
        assertThat(restaurantAllocated).isNotNull().isEqualTo(restaurantEntity);
        //
        verify(restaurantRepository, times(1)).save(restaurantEntity);

    }

    @Test
    void shouldAllowFindRestaurant(){
        //arrange
        var id = UUID.randomUUID();
        var restaurantEntity = generateRestaurantEntity();
        restaurantEntity.setId(id);
        when(restaurantRepository.findById(any(UUID.class))).thenReturn(Optional.of(restaurantEntity));

        //act
        var returnedRestaurantEntityOptional = restaurantRepository.findById(id);

        //assert
        assertThat(returnedRestaurantEntityOptional).isPresent().containsSame(restaurantEntity);
        // garante que o customer recebido é o customer que foi gerado

        returnedRestaurantEntityOptional.ifPresent(restaurantReceived ->{
            assertThat(restaurantReceived.getId()).isEqualTo(restaurantEntity.getId());
            assertThat(restaurantReceived.getName()).isEqualTo(restaurantEntity.getName());
        });
        verify(restaurantRepository, times(1)).findById(any(UUID.class));



    }

    @Test
    void shouldAllowListCustomers(){
        // arrange
        var restaurant1 = generateRestaurantEntity();
        var restaurant2 = generateRestaurantEntity();
        var restaurantList = Arrays.asList(restaurant1, restaurant2);

        when(restaurantRepository.findAll()).thenReturn(restaurantList);

        // act
        var restaurantReceived = restaurantRepository.findAll();

        // assert
        assertThat(restaurantReceived).hasSize(2).containsExactlyInAnyOrder(restaurant1, restaurant2);
        verify(restaurantRepository, times(1)).findAll();

    }

    @Test
    void shouldExcludeRestaurant() {
        fail("validation not implemented");
    }

    private RestaurantEntity generateRestaurantEntity(){
        return RestaurantEntity.builder()
                .cnpj("00.000.000/0000-00")
                .name("Brazilian Restaurant")
                .foodType("Northeast Food")
                .startingHour(LocalTime.parse("12:00"))
                .finishingHour(LocalTime.parse("15:00"))
                .postalCode("000000-000")
                .addressNumber(2)
                .openOnlyOnBusinessDay(true)
                .build();
    }

}
