package tic1.server.entities;

import javax.persistence.*;
@Entity
@Table(name= "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @Column
    private int m;
    @Column
    private int n;
    //falta cine
    @ManyToOne
    @JoinColumn(name = "cine_id")
    private Cine cine;
    private Seat[][] disponibilidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salasId")
    public Movie movie;
    public Sala() {
        this.disponibilidad =new Seat[m][n];
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Seat[][] getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Seat[][] disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
