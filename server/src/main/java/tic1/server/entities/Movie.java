package tic1.server.entities;

import tic1.commons.transfers.NewMovieDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private  String  name;

    @Column
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @Column
    private long duration;

    @ManyToMany(cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
    private Set<Genre> genres;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Actor> actors;



    public Movie() {
    }


    public Movie(NewMovieDTO temp) {
        this.actors = temp.getActors().stream().map(Actor::new).collect(Collectors.toSet());
        this.description = temp.getDescription();
        this.duration = temp.getDuration();
       // this.id = temp.getId();
        this.name = temp.getName();
        this.genres = temp.getGenres().stream().map(Genre::new).collect(Collectors.toSet());
        this.imagePath = temp.getImagePath();
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

    public NewMovieDTO toDTO() {
        NewMovieDTO movieDTO = new NewMovieDTO();
        movieDTO.setActors(this.actors.stream().map(Actor::toDTO).collect(Collectors.toSet()));
        movieDTO.setDescription(this.description);
        movieDTO.setImagePath(this.imagePath);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        movieDTO.setGenres(this.genres.stream().map(Genre::toDTO).collect(Collectors.toSet()));
        return movieDTO;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Actor> getActors() {
        return actors;
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

    public void addGenre(Genre genre) {
        //prevent endless loop
        if (this.actors.contains(genre))
            return ;
        //add new follower
        genres.add(genre);
    }

    public void removeActor(Genre genre) {
        //prevent endless loop
        if (!actors.contains(genre))
            return ;
        //remove the follower
        genres.remove(genre);
        //remove myself from the follower
        //  follower.stopFollowingTwitter(this);
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
