package com.example.jwtdemo2.services;
import java.util.*;

import com.example.jwtdemo2.models.Rating;
import com.example.jwtdemo2.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    RatingRepository repository;



    public Rating createRating(Long userId,Rating rate){
        rate.setId(userId);
        //rate.setMovieId(movieId);
        return repository.save(rate);

    }

    public List<Rating> findRatingByMovieId(Long movieId){

        return repository.findRatingByMovieId(movieId);
    }
}
