package tic1.server.entities;

import javax.persistence.*;

@Entity
@Table
public class Sala {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    private Cinema cinema;

    public Sala() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
