package com.example.jwtdemo2.repository;

import com.example.jwtdemo2.models.Rating;
import com.example.jwtdemo2.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating,Integer> {

    @Query(value="SELECT * FROM ratings WHERE movie_id=:id", nativeQuery = true)
    public List<Rating> findRatingByMovieId(@Param("id") Long Id);
}
