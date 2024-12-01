package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.persistence.RestaurantEntity;
import com.yajima.restaurants.infra.persistence.RestaurantRepository;
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
public class RestaurantJpaRepositoryIT {
    //Testes de integração

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void shouldAllowCreateTable(){
        var totalOfRecords = restaurantRepository.count();
        assertThat(totalOfRecords).isNotNegative();
    }

    @Test
    void shouldAllowCreateRestaurant(){
        // arrange
        var restaurantEntity = generateRestaurantEntity();

        // act
        var receivedRestaurant = restaurantRepository.save(restaurantEntity);

        //assert
        assertThat(receivedRestaurant)
                .isInstanceOf(RestaurantEntity.class)
                .isNotNull();

        assertThat(receivedRestaurant.getName()).isEqualTo(restaurantEntity.getName());
    }
    @Test
    void shouldAllowFindRestaurant(){
        //arrange
        var restaurantEntity = generateRestaurantEntity();
        RestaurantEntity savedRestaurantEntity = saveRestaurant(restaurantEntity);

        // act
        var receivedRestaurantEntityOptional = restaurantRepository.findById(savedRestaurantEntity.getId());

        // assert
        assertThat(receivedRestaurantEntityOptional)
                .isPresent();
        receivedRestaurantEntityOptional.ifPresent(receivedRestaurantEntity -> {
            assertThat(receivedRestaurantEntity.getName()).isEqualTo(savedRestaurantEntity.getName());
        });
    }

    @Test
    void shouldAllowListRestaurants(){
        var restaurantEntity1 = generateRestaurantEntity();
        var receivedRestaurant1 = restaurantRepository.save(restaurantEntity1);

        var restaurantEntity2 = generateRestaurantEntity();
        var receivedRestaurant2 = restaurantRepository.save(restaurantEntity2);

        var restaurantsList = restaurantRepository.findAll();
        assertThat(restaurantsList).hasSizeGreaterThan(0);


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

    private  RestaurantEntity saveRestaurant(RestaurantEntity restaurantEntity){
        return restaurantRepository.save(restaurantEntity);
    }
}
