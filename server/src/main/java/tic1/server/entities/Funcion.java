package tic1.server.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Funcion {
    @EmbeddedId
    @Id
    private FuncioPK id;

    public Funcion(FuncioPK id, Movie movie) {
        this.id = id;
        this.movie = movie;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Funcion() {
    }

    public FuncioPK getId() {
        return id;
    }

    public void setId(FuncioPK id) {
        this.id = id;
    }

    public Sala getSalon() {
        return this.id.getSalon() ;
    }

    public void setSalon(Sala salon) {
        this.id.setSalon(salon);
    }

    public LocalDate getFecha() {
        return id.getFecha();
    }

    public void setFecha(LocalDate fecha) {
        id.setFecha(fecha);
    }
}
