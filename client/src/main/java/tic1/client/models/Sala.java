package tic1.client.models;

import tic1.commons.transfers.SalaDTO;

public class Sala {
    private long id;

    private String name;

    private String cinemaName;

    public Sala() {
    }


    public Sala(SalaDTO dto) {
        this.setCinemaName(dto.getCinemaName());
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

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
}
