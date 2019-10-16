package tic1.client.models;

import tic1.commons.transfers.MovieDTO;

public class Movie {
    String name;
    String description;
    String actors;
    String duration;

    public Movie() {
        this.name = null;
    }

    public Movie(MovieDTO movie){
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.actors = movie.getActors();
        this.duration = movie.getDuration();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}