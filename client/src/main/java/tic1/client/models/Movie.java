package tic1.client.models;

import tic1.commons.transfers.NewMovieDTO; //TODO auqnue lo muestra no esta compliandolo :(

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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

    private Set<Genre> genres;

    private String imagePath;

    public Movie() {
        this.name = null;
    }

    public Movie(NewMovieDTO movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.actors = movie.getActors().stream().map(Actor::new).collect(Collectors.toSet());
        this.duration = movie.getDuration();
        this.genres = movie.getGenres().stream().map(Genre::new).collect(Collectors.toSet());
        this.imagePath = movie.getImagePath();
        //this.genres = movie.getGenres().stream().map(Genre::new).distinct().collect(Collectors.toList());
    }

    public NewMovieDTO toDTO() {
        NewMovieDTO movieDTO = new NewMovieDTO();
        movieDTO.setActors(this.actors.stream().map(Actor::toDTO).collect(Collectors.toSet()));
        movieDTO.setDescription(this.description);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        movieDTO.setGenres(this.genres.stream().map(Genre::toDTO).collect(Collectors.toSet()));
        movieDTO.setImagePath(this.getImagePath());
        return movieDTO;
    }


    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public void addActor(Actor actor) {
        //prevent endless loop
        if (this.actors.contains(actor))
            return;
        //add new follower
        actors.add(actor);
    }


    public void removeActor(Actor actor) {
        //prevent endless loop
        if (!actors.contains(actor))
            return;
        //remove the follower
        actors.remove(actor);
        //remove myself from the follower
        //  follower.stopFollowingTwitter(this);
    }

    public void addGenre(Genre genre) {
        //prevent endless loop
        if (this.genres.contains(genre))
            return;
        //add new follower
        this.genres.add(genre);
    }


    public void removeGenre(Genre genre) {
        //prevent endless loop
        if (!this.genres.contains(genre))
            return;
        //remove the follower
        this.genres.remove(genre);
        //remove myself from the follower
        //  follower.stopFollowingTwitter(this);
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Set<Genre> getGenre() {
        return this.genres;
    }

    public void setGenre(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Movie) {
            Movie movie = (Movie) o;
            return getId() == movie.getId();
        } else {
            return false;
        }
    }
}
