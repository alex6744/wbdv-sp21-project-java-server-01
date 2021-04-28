package com.example.jwtdemo2.repository;

import com.example.jwtdemo2.models.MovieRating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface MovieRatingRepository extends CrudRepository<MovieRating,Integer> {

    @Query(value="SELECT * FROM movie_ratings WHERE movie_id=:id", nativeQuery = true)
    public List<MovieRating> findRatingByMovieId(@Param("id") Long Id);

    @Modifying
    @Query(value = "insert into movie_ratings (movie_id,user_id,comment,rate) VALUES (:id,:uid,:comment,:rate)", nativeQuery = true)
    @Transactional
    public int insertMovieRating( @Param("id") Long id,@Param("uid") Long uid,@Param("comment") String comment,@Param("rate") Integer rate);

    @Modifying
    @Query(value = "DELETE FROM  movie_ratings where rating_id=:id", nativeQuery = true)
    @Transactional
    public int deleteRating( @Param("id") Integer id);


    @Query (value = "SELECT * FROM movie_ratings WHERE user_id=:id", nativeQuery = true)
    public  List<MovieRating> findRatingById (@Param("id") Long Id);
}
