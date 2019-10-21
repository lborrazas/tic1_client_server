package tic1.server.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Funcion {
    @EmbeddedId
    @Id
    private FuncionPK id;

    public Funcion(FuncionPK id, Movie movie) {
        this.id = id;
        this.movie = movie;
    }
//funcion sala una tabla aparte
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Funcion() {
    }

    public FuncionPK getId() {
        return id;
    }

    public void setId(FuncionPK id) {
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
