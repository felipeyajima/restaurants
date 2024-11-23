package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.review.CreateReview;
import com.yajima.restaurants.application.usecases.review.DeleteReview;
import com.yajima.restaurants.application.usecases.review.FindReview;
import com.yajima.restaurants.application.usecases.review.ListReviews;
import com.yajima.restaurants.domain.entities.review.Review;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final CreateReview createReview;
    private final ListReviews listReviews;
    private final FindReview findReview;

    private final DeleteReview deleteReview;


    public ReviewController(CreateReview createReview, ListReviews listReviews, FindReview findReview, DeleteReview deleteReview) {
        this.createReview = createReview;
        this.listReviews = listReviews;
        this.findReview = findReview;
        this.deleteReview = deleteReview;
    }


    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto dto){
        Review saved = createReview.createReview(new Review(
                dto.getId(),
                dto.getNumberOfStars(),
                dto.getMessage(),
                dto.getDateOfReview(),
                dto.getBooking()
        ));
        return new ReviewDto(saved.getId(), saved.getNumberOfStars(), saved.getMessage(), saved.getDateOfReview(), saved.getBooking());
    }

    @GetMapping
    public List<ReviewDto> listReviews(){
        return  listReviews.getAllReviews().stream().map(r -> new ReviewDto(
                r.getId(), r.getNumberOfStars(), r.getMessage(), r.getDateOfReview(), r.getBooking()
        )).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReviewDto findReview(@PathVariable UUID id){
        Review review = findReview.findReview(id);
        return new ReviewDto(review.getId(), review.getNumberOfStars(), review.getMessage(), review.getDateOfReview(), review.getBooking());
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable UUID id){
        deleteReview.deleteReview(id);
    }


}
