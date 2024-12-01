package com.yajima.restaurants.infra.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name="review_db")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer numberOfStars;
    private String message;
    private LocalDateTime dateOfReview;

    /*
    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private RestaurantEntity restaurantEntity;
     */

    @OneToOne
    @JoinColumn(name="booking_id")
    private BookingEntity bookingEntity;


    public ReviewEntity() {
    }

    public ReviewEntity(UUID id, Integer numberOfStars, String message, LocalDateTime dateOfReview, BookingEntity bookingEntity) {
        this.id = id;
        this.numberOfStars = numberOfStars;
        this.message = message;
        this.dateOfReview = dateOfReview;
        this.bookingEntity = bookingEntity;
    }
}
