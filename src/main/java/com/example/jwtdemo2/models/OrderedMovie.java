package com.example.jwtdemo2.models;

import javax.persistence.*;

@Entity
@Table(name = "orderedMovie")
public class OrderedMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderMovie_id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movieId")
    private Movie movie;


    public OrderedMovie() {
    }


}
