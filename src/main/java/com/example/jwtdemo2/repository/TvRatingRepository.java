package com.example.jwtdemo2.repository;


import com.example.jwtdemo2.models.MovieRating;
import com.example.jwtdemo2.models.TvRating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TvRatingRepository extends CrudRepository<TvRating,Integer> {

    @Query(value="SELECT * FROM tv_ratings WHERE tv_id=:id", nativeQuery = true)
    public List<TvRating> findRatingByTvId(@Param("id") Long Id);

    @Modifying
    @Query(value = "insert into tv_ratings (tv_id,user_id,comment,rate) VALUES (:id,:uid,:comment,:rate)", nativeQuery = true)
    @Transactional
    public int insertTvRating( @Param("id") Long id,@Param("uid") Long uid,@Param("comment") String comment,@Param("rate") Integer rate);

    @Modifying
    @Query(value = "DELETE FROM  tv_ratings where rating_id=:id", nativeQuery = true)
    @Transactional
    public int deleteRating( @Param("id") Integer id);

    @Query (value = "SELECT * FROM tv_ratings WHERE user_id=:id", nativeQuery = true)
    public  List<TvRating> findRatingById (@Param("id") Long Id);
}
