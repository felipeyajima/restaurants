package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.persistence.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ReviewJpaRepositoryTest {

    @Mock
    private ReviewRepository reviewRepository;

    AutoCloseable openMocks;
    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws  Exception{
        openMocks.close();
    }


    @Test
    void shouldAllowCreateReview(){
        //arrange
        var reviewEntity = generateReviewEntity();
        when(reviewRepository.save(reviewEntity)).thenReturn(reviewEntity);
        //act
        var reviewAllocated = reviewRepository.save(reviewEntity);
        //assert
        assertThat(reviewAllocated).isNotNull().isEqualTo(reviewEntity);
        verify(reviewRepository, times(1)).save(reviewEntity);
    }

    @Test
    void shouldAllowFindReview(){
        //arrange
        var id = UUID.randomUUID();
        var reviewEntity = generateReviewEntity();
        reviewEntity.setId(id);
        when(reviewRepository.findById(any(UUID.class))).thenReturn(Optional.of(reviewEntity));

        //act
        var returnedReviewEntityOptional = reviewRepository.findById(id);

        //assert
        assertThat(returnedReviewEntityOptional).isPresent().containsSame(reviewEntity);

        returnedReviewEntityOptional.ifPresent(reviewReceived -> {
            assertThat(reviewReceived.getId()).isEqualTo(reviewEntity.getId());
        });

        verify(reviewRepository, times(1)).findById(any(UUID.class));

    }


    @Test
    void shouldAllowListReviews(){
        //arrange
        var review1 = generateReviewEntity();
        var review2 = generateReviewEntity();
        var reviewList = Arrays.asList(review1, review2);

        when(reviewRepository.findAll()).thenReturn(reviewList);

        //act
        var reviewReceived = reviewRepository.findAll();

        //assert
        assertThat(reviewReceived).hasSize(2).containsExactlyInAnyOrder(review1, review2);
        verify(reviewRepository, times(1)).findAll();
    }



    private ReviewEntity generateReviewEntity(){
        CustomerEntity customerEntity = CustomerEntity.builder()
                .cpf("333.333.333-00")
                .email("john.silva@gmail.com")
                .name("John Silva")
                .build();

        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .cnpj("00.000.000/0000-00")
                .name("Brazilian Restaurant")
                .foodType("Northeast Food")
                .startingHour(LocalTime.parse("12:00"))
                .finishingHour(LocalTime.parse("15:00"))
                .postalCode("000000-000")
                .addressNumber(2)
                .openOnlyOnBusinessDay(false)
                .build();

        TableEntity tableEntity = TableEntity.builder()
                .status("available")
                .numberOfChairs(4)
                .tableNumber(1)
                .restaurantEntity(restaurantEntity)
                .build();

        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingStart(LocalDateTime.parse("2024-11-22T12:15:30"))
                .bookingFinish(LocalDateTime.parse("2024-11-22T13:15:30"))
                .tableEntity(tableEntity)
                .customerEntity(customerEntity)
                .build();

        return  ReviewEntity.builder()
                .dateOfReview(LocalDateTime.parse("2024-11-23T13:15:30"))
                .message("very good meal")
                .numberOfStars(5)
                .bookingEntity(bookingEntity)
                .build();
    }

}
