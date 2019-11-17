package tic1.client.models;

import tic1.commons.transfers.SalaDTO;

public class Sala {
    private long id;

    private String name;

    private long cinemaId;

    private long maxcolum;

    private long maxfila;
    public Sala() {
    }


    public Sala(SalaDTO dto) {
        this.setCinemaId(dto.getCinemaid());
        this.setId(dto.getId());
        this.setName(dto.getName());
        this.maxcolum = dto.getMaxcolum();
        this.maxfila = dto.getMaxfila();
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

    public SalaDTO toDTO() {
            SalaDTO  salaDTO= new SalaDTO();
            salaDTO.setCinemaid(this.cinemaId);
            salaDTO.setId(this.id);
            salaDTO.setName(this.name);
            salaDTO.setMaxcolum(this.maxcolum);
            salaDTO.setMaxfila(this.maxfila);
            return salaDTO;
        }

}
