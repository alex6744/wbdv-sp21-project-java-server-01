package com.example.jwtdemo2.services;

import com.example.jwtdemo2.models.MovieRating;
import com.example.jwtdemo2.models.TvRating;
import com.example.jwtdemo2.repository.TvRatingRepository;
import com.example.jwtdemo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvRatingService {

    @Autowired
    TvRatingRepository repository;

    @Autowired
    UserRepository userRepository;




    public int createTvRating(Long userId, Long id, TvRating rate){


        return repository.insertTvRating(id,userId,rate.getComment(),rate.getRate());

    }

    public List<TvRating> findRatingByTvId(Long tvId){

        return repository.findRatingByTvId(tvId);
    }

    public int deleteTvRating(Integer id){

        return repository.deleteRating(id);
    }

    public List<TvRating> findRatingById(Long id){
        return repository.findRatingById(id);
    }
}
