package com.example.jwtdemo2.models;
import javax.persistence.*;


@Entity
@Table(name = "uploadedMovies")
public class UploadMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer movieId;

    private Integer userId;

    public UploadMovie() {
    }


}
