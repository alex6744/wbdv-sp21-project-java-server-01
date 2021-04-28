package com.example.jwtdemo2.services;



import com.example.jwtdemo2.models.Movie;
import com.example.jwtdemo2.models.UploadMovie;
import com.example.jwtdemo2.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository repository;


    public Movie createMovie(Long movieId, Movie movie){
        movie.setMovieId(movieId);
        return repository.save(movie);

    }


    public int insertUpload(Long movieId,Long userId,Movie movie){


        repository.save(movie);
        return repository.insertUploaded(movieId,userId);
    }

    public boolean existsMovieById(Long id){
        return repository.existsByMovieId(id);
    }


    public List<UploadMovie> findById(Long id){

        List<UploadMovie> a=repository.findByUserId(id);
        System.out.println(a.toString());
        return repository.findByUserId(id);
    }
}
