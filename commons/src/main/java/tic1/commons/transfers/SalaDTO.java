package tic1.commons.transfers;

import java.util.List;

public class SalaDTO {

    private long id;
    private long cinemaid;
    private String name;
    private long maxcolum;
    private long maxfila;
    private List<SeatDTO> seats;

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    public long getMaxcolum() {
        return maxcolum;
    }

    public void setMaxcolum(long maxcolum) {
        this.maxcolum = maxcolum;
    }

    public long getMaxfila() {
        return maxfila;
    }

    public void setMaxfila(long maxfila) {
        this.maxfila = maxfila;
    }

    public SalaDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(long cinemaid) {
        this.cinemaid = cinemaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
