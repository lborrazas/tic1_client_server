package tic1.client.models;

import tic1.commons.transfers.SalaDTO;

public class Sala {
    private long id;

    private String name;

    private long cinemaId;

    public Sala() {
    }


    public Sala(SalaDTO dto) {
        this.setCinemaId(dto.getCinemaid());
        this.setId(dto.getId());
        this.setName(dto.getName());
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

    public long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(long cinemaId) {
        this.cinemaId = cinemaId;
    }
}
