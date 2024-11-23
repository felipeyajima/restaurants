package com.yajima.restaurants.application.gateways;

import com.yajima.restaurants.domain.entities.review.Review;

import java.util.List;
import java.util.UUID;

public interface RepositoryOfReview {

    Review createReview(Review review);

    List<Review> listEverything();

    Review findReview(UUID id);

    void deleteReview(UUID id);

}
