package com.example.jwtdemo2.services;
import java.util.*;

import com.example.jwtdemo2.models.MovieRating;
import com.example.jwtdemo2.repository.MovieRepository;
import com.example.jwtdemo2.repository.MovieRatingRepository;
import com.example.jwtdemo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingService {
    @Autowired
    MovieRatingRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;


    public int createMovieRating(Long userId, Long movieId, MovieRating rate){


        return repository.insertMovieRating(movieId,userId,rate.getComment(),rate.getRate());

    }

    public int deleteMovieRating(Integer id){

        return repository.deleteRating(id);
    }

    public List<MovieRating> findRatingByMovieId(Long movieId){
        List<MovieRating> rates=repository.findRatingByMovieId(movieId);
        return repository.findRatingByMovieId(movieId);
    }
    public List<MovieRating> findRatingById(Long id){
        return repository.findRatingById(id);
    }
}
