package tic1.commons.transfers;

import java.util.List;

public class SalaDTO {

    private long id;
    private long cinemaid;
    private String name;
    private long maxcolum;
    private long maxfila;

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
