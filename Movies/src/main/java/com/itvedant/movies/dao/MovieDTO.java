package com.itvedant.movies.dao;

public class MovieDTO {
    private Integer id;
    private String title;

    public MovieDTO(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
}
