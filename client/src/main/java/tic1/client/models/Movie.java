package tic1.client.models;

import tic1.commons.transfers.NewMovieDTO; //TODO auqnue lo muestra no esta compliandolo :(

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Movie {

    private long id;
    private String name;
    private String description;
    private List<Actor> actors;
    private long duration;
    private List<Genre> genre;

    public Movie() {
        this.name = null;
    }

    public Movie(NewMovieDTO movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.actors = movie.getActors().stream().map(Actor::new).collect(Collectors.toList());
        this.duration = movie.getDuration();
        this.genre = movie.getGenres().stream().map(Genre::new).collect(Collectors.toList());
    }

    public NewMovieDTO toDTO() {
        NewMovieDTO movieDTO = new NewMovieDTO();
        movieDTO.setActors(this.actors.stream().map(Actor::toDTO).collect(Collectors.toList()));
        movieDTO.setDescription(this.description);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        movieDTO.setGenres(this.genre.stream().map(Genre::toDTO).collect(Collectors.toList()));
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

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void addActor(Actor actor) {
        //prevent endless loop
        if (this.actors.contains(actor))
            return ;
        //add new follower
        actors.add(actor);
    }


    public void removeActor(Actor actor) {
        //prevent endless loop
        if (!actors.contains(actor))
            return ;
        //remove the follower
        actors.remove(actor);
        //remove myself from the follower
        //  follower.stopFollowingTwitter(this);
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }
}
