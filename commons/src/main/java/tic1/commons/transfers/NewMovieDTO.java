package tic1.commons.transfers;

import java.util.List;

public class NewMovieDTO {
    private long id;
    private String description;
    private long duration;
    private List<MovieActorDTO> actors;
    private String name;
    private List<MovieGenreDTO> genres;


    public NewMovieDTO() {
    }

    public List<MovieGenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieGenreDTO> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<MovieActorDTO> getActors() {
        return actors;
    }

    public void setActors(List<MovieActorDTO> actors) {
        this.actors = actors;
    }
}
