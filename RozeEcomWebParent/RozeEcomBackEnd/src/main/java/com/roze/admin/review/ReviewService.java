package com.roze.admin.review;

import com.roze.admin.paging.PagingAndSortingHelper;
import com.roze.admin.product.ProductRepository;
import com.roze.common.entity.Review;
import com.roze.common.exception.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class ReviewService {
    public static final int REVIEWS_PER_PAGE = 5;

    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private ProductRepository productRepo;

    public void listByPage(int pageNum, PagingAndSortingHelper helper) {
        helper.listEntities(pageNum, REVIEWS_PER_PAGE, reviewRepo);
    }

    public Review get(Integer id) throws ReviewNotFoundException {
        try {
            return reviewRepo.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new ReviewNotFoundException("Could not find any reviews with ID " + id);
        }
    }

    public void save(Review reviewInForm) {
        Review reviewInDB = reviewRepo.findById(reviewInForm.getId()).get();
        reviewInDB.setHeadline(reviewInForm.getHeadline());
        reviewInDB.setComment(reviewInForm.getComment());

        reviewRepo.save(reviewInDB);
        productRepo.updateReviewCountAndAverageRating(reviewInDB.getProduct().getId());
    }

    public void delete(Integer id) throws ReviewNotFoundException {
        if (!reviewRepo.existsById(id)) {
            throw new ReviewNotFoundException("Could not find any reviews with ID " + id);
        }

        reviewRepo.deleteById(id);
    }
}
