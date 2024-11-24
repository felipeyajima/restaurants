package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.booking.FindBooking;
import com.yajima.restaurants.application.usecases.review.CreateReview;
import com.yajima.restaurants.application.usecases.review.DeleteReview;
import com.yajima.restaurants.application.usecases.review.FindReview;
import com.yajima.restaurants.application.usecases.review.ListReviews;
import com.yajima.restaurants.domain.entities.booking.Booking;
import com.yajima.restaurants.domain.entities.customer.Customer;
import com.yajima.restaurants.domain.entities.restaurant.Restaurant;
import com.yajima.restaurants.domain.entities.review.Review;
import com.yajima.restaurants.domain.entities.table.Table;
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

    private final FindBooking findBooking;


    public ReviewController(CreateReview createReview, ListReviews listReviews, FindReview findReview, DeleteReview deleteReview, FindBooking findBooking) {
        this.createReview = createReview;
        this.listReviews = listReviews;
        this.findReview = findReview;
        this.deleteReview = deleteReview;
        this.findBooking = findBooking;
    }


    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto dto){
        Booking booking = findBooking.findBooking(dto.getBooking().getId());

        Review saved = createReview.createReview(new Review(
                dto.getId(),
                dto.getNumberOfStars(),
                dto.getMessage(),
                dto.getDateOfReview(),
                new Booking(
                        booking.getId(),
                        booking.getStatus(),
                        booking.getBookingStart(),
                        booking.getBookingFinish(),
                        new Table(
                                booking.getTable().getId(),
                                booking.getTable().getTableNumber(),
                                booking.getTable().getNumberOfChairs(),
                                booking.getTable().getStatus(),
                                new Restaurant(
                                        booking.getTable().getRestaurant().getId(),
                                        booking.getTable().getRestaurant().getName(),
                                        booking.getTable().getRestaurant().getCnpj(),
                                        booking.getTable().getRestaurant().getFoodType(),
                                        booking.getTable().getRestaurant().getStartingHour(),
                                        booking.getTable().getRestaurant().getFinishingHour(),
                                        booking.getTable().getRestaurant().getPostalCode(),
                                        booking.getTable().getRestaurant().getAddressNumber(),
                                        booking.getTable().getRestaurant().getOpenOnlyOnBusinessDay()
                                )
                        ),
                        new Customer(
                                booking.getCustomer().getId(),
                                booking.getCustomer().getName(),
                                booking.getCustomer().getCpf(),
                                booking.getCustomer().getEmail()
                        )
                )
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
