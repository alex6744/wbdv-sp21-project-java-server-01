package com.example.jwtdemo2.repository;

import com.example.jwtdemo2.models.Movie;
import com.example.jwtdemo2.models.TvRating;
import com.example.jwtdemo2.models.UploadMovie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {
    Boolean existsByMovieId(Long id);
    Optional<Movie> findByMovieId(Long id);



    @Query(value="SELECT * FROM uploaded_movies WHERE user_id=:id", nativeQuery = true)
    public List<UploadMovie> findByUserId(@Param("id") Long Id);

    @Modifying
    @Query(value = "insert into uploaded_movies (movie_id,user_id) VALUES (:movie_id,:user_id)", nativeQuery = true)
    @Transactional
    int insertUploaded(@Param("movie_id") Long movieId, @Param("user_id") Long user_id);

}
