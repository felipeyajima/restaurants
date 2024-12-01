package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.infra.persistence.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ReviewJpaRepositoryIT {

    // testes de integração

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private TableRepository tableRepository;


    @Test
    void shouldAllowCreateDatabaseTable(){
        var totalOfRecords = reviewRepository.count();
        assertThat(totalOfRecords).isNotNegative();
    }

    @Test
    void shouldAllowCreateReview(){
        //arrange
        var reviewEntity = generateReviewEntity();
        //act
        var receivedReview = reviewRepository.save(reviewEntity);
        //assert
        assertThat(receivedReview)
                .isInstanceOf(ReviewEntity.class)
                .isNotNull();
        assertThat(receivedReview.getBookingEntity()).isEqualTo(reviewEntity.getBookingEntity());
    }


    @Test
    void shouldAllowFindReview(){
        //arrange
        var reviewEntity = generateReviewEntity();
        ReviewEntity savedReviewEntity = saveReview(reviewEntity);
        //act
        var receivedReviewEntityOptional = reviewRepository.findById(savedReviewEntity.getId());
        //assert
        assertThat(receivedReviewEntityOptional).isPresent();
        receivedReviewEntityOptional.ifPresent(receivedReviewEntity -> {
            assertThat(receivedReviewEntity.getMessage()).isEqualTo(savedReviewEntity.getMessage());
        });
    }

    @Test
    void shouldAllowListReviews(){
        var reviewEntity1 = generateReviewEntity();
        ReviewEntity savedReviewEntity1 = saveReview(reviewEntity1);

        var reviewEntity2 = generateReviewEntity();
        ReviewEntity savedReviewEntity2 = saveReview(reviewEntity2);

        var reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSizeGreaterThan(0);
    }






    private ReviewEntity generateReviewEntity(){
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


        TableEntity tableEntity = TableEntity.builder()
                .tableNumber(2)
                .numberOfChairs(2)
                .status("available")
                .restaurantEntity(savedRestaurant)
                .build();

        TableEntity savedTable = tableRepository.save(tableEntity);

        CustomerEntity customerEntity = CustomerEntity.builder()
                .name("Joseph")
                .cpf("222.222.222-00")
                .email("joseph@gmail.com")
                .build();

        CustomerEntity savedcustomer = customerRepository.save(customerEntity);


        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingStart(LocalDateTime.parse("2024-11-22T12:15:30"))
                .bookingFinish(LocalDateTime.parse("2024-11-22T13:15:30"))
                .tableEntity(savedTable)
                .customerEntity(savedcustomer)
                .build();

        BookingEntity savedBooking = bookingRepository.save(bookingEntity);


        return ReviewEntity.builder()
                .dateOfReview(LocalDateTime.parse("2024-11-22T12:15:30"))
                .message("very good meal")
                .bookingEntity(savedBooking)
                .build();

    }

    private ReviewEntity saveReview(ReviewEntity reviewEntity){
        return reviewRepository.save(reviewEntity);
    }

}
