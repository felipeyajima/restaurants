package com.yajima.restaurants.infra.gateway;

import com.yajima.restaurants.application.gateways.RepositoryOfReview;
import com.yajima.restaurants.domain.entities.review.Review;
import com.yajima.restaurants.infra.controller.exceptions.ControllerNotFoundException;
import com.yajima.restaurants.infra.persistence.ReviewEntity;
import com.yajima.restaurants.infra.persistence.ReviewRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReviewJpaRepository implements RepositoryOfReview {

    private final ReviewRepository repository;
    private final ReviewEntityMapper mapper;

    public ReviewJpaRepository(ReviewRepository repository, ReviewEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Review createReview(Review review) {
        ReviewEntity entity = mapper.toEntity(review);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Review> listEverything() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Review findReview(UUID id) {
        ReviewEntity review = repository.findById(id).orElseThrow(()-> new ControllerNotFoundException("review not found"));
        return mapper.toDomain(review);
    }

    @Override
    public void deleteReview(UUID id) {
        this.repository.deleteById(id);
    }
}
