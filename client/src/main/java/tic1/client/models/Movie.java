package tic1.client.models;

import tic1.commons.transfers.MovieDTO; //TODO auqnue lo muestra no esta compliandolo :(



public class Movie {

    private long id;
    private String name;
    private String description;
    private String actors;
    private String duration;

    public Movie() {
        this.name = null;
    }

    public Movie(MovieDTO movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.actors = movie.getActors();
        this.duration = movie.getDuration();
    }

    public Movie(long id, String name, String description, String actors, String duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.actors = actors;
        this.duration = duration;
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

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actors='" + actors + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MovieDTO toDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setActors(this.actors);
        movieDTO.setDescription(this.description);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
//        movieDTO.setId(this.id);
        return movieDTO;
    }
}
