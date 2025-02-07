package com.vic.ecommerce.service;

import com.vic.ecommerce.exception.ReviewNotFoundException;
import com.vic.ecommerce.model.Product;
import com.vic.ecommerce.model.Review;
import com.vic.ecommerce.model.User;
import com.vic.ecommerce.request.CreateReviewRequest;

import javax.naming.AuthenticationException;
import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest req,
                        User user,
                        Product product);

    List<Review> getReviewsByProductId(Long productId);

    Review updateReview(Long reviewId,
                        String reviewText,
                        double rating,
                        Long userId) throws ReviewNotFoundException, AuthenticationException;


    void deleteReview(Long reviewId, Long userId) throws ReviewNotFoundException, AuthenticationException;

}
