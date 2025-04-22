package com.itvedant.movies.dao;

public class TheaterDTO {
    private Integer id;
    private String name;
    private String location;

    public TheaterDTO(Integer id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
}
