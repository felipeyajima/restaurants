package com.yajima.restaurants.application.usecases.review;

import com.yajima.restaurants.application.gateways.RepositoryOfReview;
import com.yajima.restaurants.domain.entities.review.Review;

import java.util.UUID;

public class FindReview {

    public final RepositoryOfReview repository;


    public FindReview(RepositoryOfReview repository) {
        this.repository = repository;
    }

    public Review findReview(UUID id){return this.repository.findReview(id);}
}
