package tic1.client.models;

import tic1.commons.transfers.NewMovieDTO; //TODO auqnue lo muestra no esta compliandolo :(

import java.util.Set;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Movie {

    private long id;
    private String name;
    private String description;
    private Set<Actor> actors;
    private long duration;
    private Set<Genre> genre;

    public Movie() {
        this.name = null;
    }

    public Movie(NewMovieDTO movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.actors = movie.getActors().stream().map(Actor::new).collect(Collectors.toSet());
        this.duration = movie.getDuration();
        this.genre = movie.getGenres().stream().map(Genre::new).collect(Collectors.toSet());
    }

    public NewMovieDTO toDTO() {
        NewMovieDTO movieDTO = new NewMovieDTO();
        movieDTO.setActors(this.actors.stream().map(Actor::toDTO).collect(Collectors.toSet()));
        movieDTO.setDescription(this.description);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        movieDTO.setGenres(this.genre.stream().map(Genre::toDTO).collect(Collectors.toSet()));
        return movieDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Set<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }
}
