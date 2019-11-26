package tic1.server.entities;

import tic1.commons.transfers.SalaDTO;
import tic1.server.business.CinemaMgr;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column
    private String name;


    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cinema")
    private Cinema cinema;
    @Column
    private long maxColumn;
    @Column
    private long maxFila;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER ,mappedBy = "id.sala")
    private List<Seat> seats;

    public Sala() {
    }

    public Sala(SalaDTO dto){

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

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public long getMaxColumn() {
        return maxColumn;
    }

    public void setMaxColumn(long maxColumn) {
        this.maxColumn = maxColumn;
    }

    public long getMaxFila() {
        return maxFila;
    }

    public void setMaxFila(long maxFila) {
        this.maxFila = maxFila;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public SalaDTO toDTO() {
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setCinemaid(this.cinema.getId());
        salaDTO.setId(this.id);
        salaDTO.setName(this.name);
        salaDTO.setMaxcolum(this.maxColumn);
        salaDTO.setMaxfila(this.maxFila);
        salaDTO.setSeats(this.seats.stream().map(Seat::toDTO).collect(Collectors.toList()));
        return salaDTO;
    }
}
