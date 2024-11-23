package com.yajima.restaurants.application.usecases.review;

import com.yajima.restaurants.application.gateways.RepositoryOfReview;

import java.util.UUID;

public class DeleteReview {

    public final RepositoryOfReview repository;


    public DeleteReview(RepositoryOfReview repository) {
        this.repository = repository;
    }

    public void deleteReview(UUID id){this.repository.deleteReview(id);}
}
