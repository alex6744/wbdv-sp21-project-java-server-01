package com.example.jwtdemo2.models;
import javax.persistence.*;


@Entity
@Table(name = "uploadedMovies")
public class UploadMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long movieId;

    private Long userId;

    public UploadMovie() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
