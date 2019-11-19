package tic1.server.entities;

import tic1.commons.transfers.SalaDTO;

import javax.persistence.*;

@Entity
@Table
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.REMOVE,fetch=FetchType.EAGER)
    @JoinColumn(name = "id_cinema")
    private Cinema cinema;
    @Column
    private long Maxcolum;
    @Column
    private long Maxfila;

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

    public long getMaxcolum() {
        return Maxcolum;
    }

    public void setMaxcolum(long maxcolum) {
        Maxcolum = maxcolum;
    }

    public long getMaxfila() {
        return Maxfila;
    }

    public void setMaxfila(long maxfila) {
        Maxfila = maxfila;
    }

    public SalaDTO toDTO() {
        SalaDTO  salaDTO= new SalaDTO();
        salaDTO.setCinemaid(this.cinema.getId());
        salaDTO.setId(this.id);
        salaDTO.setName(this.name);
        salaDTO.setMaxcolum(this.Maxcolum);
        salaDTO.setMaxfila(this.Maxfila);
        return salaDTO;
    }
}
