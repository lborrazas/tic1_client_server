package tic1.client.models;

import tic1.commons.transfers.MovieActorDTO;
import tic1.commons.transfers.MovieDTO; //TODO auqnue lo muestra no esta compliandolo :(
import tic1.commons.transfers.NewMovieDTO;

import java.util.List;


public class Movie {

    private long id;
    private String name;
    private String description;
    private List<String> actors;
    private long duration;
    private List<String> genre;

    public Movie() {
        this.name = null;
    }

    public Movie(NewMovieDTO movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.actors = movie.getActors();
        this.duration = movie.getDuration();
        this.genre = movie.getGenres();
    }

    public Movie(long id, String name, String description, List<String> actors, long duration, String genre) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.actors = actors;
        this.duration = duration;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void addActor(String actor) {
        this.actors.add(actor);
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actors=" + actors +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public NewMovieDTO toDTO() {
        NewMovieDTO movieDTO = new NewMovieDTO();
        movieDTO.setActors(this.actors);
        movieDTO.setDescription(this.description);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        movieDTO.setGenres(this.genre);
        return movieDTO;
    }
}
