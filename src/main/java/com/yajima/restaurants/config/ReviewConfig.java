package com.yajima.restaurants.config;

import com.yajima.restaurants.application.gateways.RepositoryOfReview;
import com.yajima.restaurants.application.usecases.review.CreateReview;
import com.yajima.restaurants.application.usecases.review.DeleteReview;
import com.yajima.restaurants.application.usecases.review.FindReview;
import com.yajima.restaurants.application.usecases.review.ListReviews;
import com.yajima.restaurants.infra.gateway.ReviewEntityMapper;
import com.yajima.restaurants.infra.gateway.ReviewJpaRepository;
import com.yajima.restaurants.infra.persistence.ReviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewConfig {

    @Bean
    CreateReview createReview(RepositoryOfReview repositoryOfReview){return new CreateReview(repositoryOfReview);}

    @Bean
    ListReviews listReviews(RepositoryOfReview repositoryOfReview){ return new ListReviews(repositoryOfReview);}

    @Bean
    FindReview findReview(RepositoryOfReview repositoryOfReview){ return new FindReview(repositoryOfReview);}

    @Bean
    DeleteReview deleteReview(RepositoryOfReview repositoryOfReview){return new DeleteReview(repositoryOfReview);}

    @Bean
    ReviewJpaRepository reviewJpaRepository(ReviewRepository repository, ReviewEntityMapper mapper){
        return new ReviewJpaRepository(repository, mapper);
    }

    @Bean
    ReviewEntityMapper returnMapperReview(){return  new ReviewEntityMapper();}


}
