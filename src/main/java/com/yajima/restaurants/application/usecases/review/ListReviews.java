package com.yajima.restaurants.application.usecases.review;

import com.yajima.restaurants.application.gateways.RepositoryOfReview;
import com.yajima.restaurants.domain.entities.review.Review;

import java.util.List;

public class ListReviews {

    private final RepositoryOfReview repository;


    public ListReviews(RepositoryOfReview repository) {
        this.repository = repository;
    }

    public List<Review> getAllReviews(){return this.repository.listEverything();}
}
