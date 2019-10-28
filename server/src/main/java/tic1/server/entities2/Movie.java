package tic1.server.entities2;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movieGenre")
    private List<Genre> genres;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cast")
    private List<Actor> actors;

    @Lob
    @Column(name = "imagen_Cartelera",columnDefinition = "largeLob")
    private Byte[] imageCartelera;

    public Movie() {
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

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Byte[] getImage() {
        return imageCartelera;
    }

    public void setImageCartelera(Byte[] imageCartelera) {
        this.imageCartelera = imageCartelera;
    }
}
