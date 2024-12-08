package com.yajima.restaurants.infra.controller;

import com.yajima.restaurants.application.usecases.booking.FindBooking;
import com.yajima.restaurants.application.usecases.review.CreateReview;
import com.yajima.restaurants.application.usecases.review.DeleteReview;
import com.yajima.restaurants.application.usecases.review.FindReview;
import com.yajima.restaurants.application.usecases.review.ListReviews;
import com.yajima.restaurants.domain.entities.review.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateReview createReview;
    @Mock
    private DeleteReview deleteReview;
    @Mock
    private FindReview findReview;
    @Mock
    private ListReviews listReviews;
    @Mock
    private FindBooking findBooking;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        ReviewController reviewController = new ReviewController(createReview, listReviews, findReview, deleteReview, findBooking);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController)
                .build();
    }

    @AfterEach
    void tearDown() throws  Exception{
        mock.close();
    }


    @Test
    void shouldFindReviews() throws Exception{
        List<Review> lista = new ArrayList<>();
        when(listReviews.getAllReviews()).thenReturn(lista);
        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk());

    }


}
