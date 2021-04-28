package com.example.jwtdemo2.models;

import java.util.*;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    private Long movieId;
    private String original_title;
    private String release_date;

    private  String original_language;

    private  String overview;

    @OneToMany(mappedBy = "movie")
    Set<OrderedMovie> orderedMovie;

    @OneToMany(mappedBy = "movie")
    Set<MovieRating> rates;

    public Movie() {
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
