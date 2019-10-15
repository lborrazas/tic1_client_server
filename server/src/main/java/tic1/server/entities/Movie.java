package tic1.server.entities;

import tic1.commons.transfers.MovieDTO;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String actors;

    @Column(nullable = false)
    private String duration;

    public Movie(MovieDTO temp)  {

        this.actors = temp.getActors();
        this.description = temp.getDescription();
        this.duration = temp.getDuration();
        this.id = temp.getId();
        this.name = temp.getName();

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

    public MovieDTO toDTO() {
       MovieDTO movieDTO = new MovieDTO();
        movieDTO.setActors(this.actors);
        movieDTO.setDescription(this.description);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);

        return movieDTO;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actors='" + actors + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
