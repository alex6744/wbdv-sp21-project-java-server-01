package com.example.jwtdemo2.controllers;

public class rateDemo {

    private Integer id;
    private String comment;
    private Integer rate;
    private String username;
    private Long itemId;

    public rateDemo(Integer id, String comment, Integer rate, String username, Long itemId) {
        this.id = id;
        this.comment = comment;
        this.rate = rate;
        this.username = username;
        this.itemId =  itemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }



    public Long getItemId() {
        return  itemId;
    }

    public void setItemId(Long  itemId) {
        this.itemId =  itemId;
    }
}
