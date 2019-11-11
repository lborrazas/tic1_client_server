package tic1.commons.transfers;

import java.util.List;
import java.util.Set;

public class NewMovieDTO {
    private long id;
    private String description;
    private long duration;
    private Set<MovieActorDTO> actors;
    private String name;
    private Set<MovieGenreDTO> genres;


    public NewMovieDTO() {
    }

    public Set<MovieGenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<MovieGenreDTO> genres) {
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

    public Set<MovieActorDTO> getActors() {
        return actors;
    }

    public void setActors(Set<MovieActorDTO> actors) {
        this.actors = actors;
    }


}
