package tic1.server.entities;

import tic1.commons.transfers.NewMovieDTO;

import javax.persistence.*;
import java.util.List;
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


    @Column
    public long duration;

    @ManyToMany(cascade = CascadeType.MERGE)

    private List<Genre> genres;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Actor> actors;


    public Movie() {
    }


    public Movie(NewMovieDTO temp)  {

        this.actors = temp.getActors().stream().map(Actor::new).collect(Collectors.toList());
        this.description = temp.getDescription();
        this.duration = temp.getDuration();
        this.id = temp.getId();
        this.name = temp.getName();
        this.genres = temp.getGenres().stream().map(Genre::new).collect(Collectors.toList());

    }

   // public Byte[] getImageCartelera() {
        //return imageCartelera;
    //}

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
        movieDTO.setActors(this.actors.stream().map(Actor::toDTO).collect(Collectors.toList()));
        movieDTO.setDescription(this.description);
        movieDTO.setDuration(this.duration);
        movieDTO.setName(this.name);
        movieDTO.setId(this.id);
        movieDTO.setGenres(this.genres.stream().map(Genre::toDTO).collect(Collectors.toList()));
        return movieDTO;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Actor> getActors() {
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

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
