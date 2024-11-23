package com.yajima.restaurants.application.usecases.review;

import com.yajima.restaurants.application.gateways.RepositoryOfReview;
import com.yajima.restaurants.domain.entities.review.Review;

public class CreateReview {

    private final RepositoryOfReview repository;


    public CreateReview(RepositoryOfReview repository) {
        this.repository = repository;
    }

    public Review createReview(Review review){return this.repository.createReview(review);}
}
