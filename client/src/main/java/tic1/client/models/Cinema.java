package tic1.client.models;

import tic1.commons.transfers.CinemaDto;

public class Cinema {
    private long id;

    private String name;

    private String location;

    private long provider;

    public  Cinema(CinemaDto cinemaDto){
        this.name= cinemaDto.getName();
        this.provider = cinemaDto.getProviderId();
        this.location =cinemaDto.getLocation();
        this.id =cinemaDto.getId();

    }

    public  Cinema(){

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getProvider() {
        return provider;
    }

    public void setProvider(long provider) {
        this.provider = provider;
    }

    public CinemaDto toDTO() {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setProvider(this.provider);
        cinemaDto.setName(this.name);
        cinemaDto.setLocation(this.location);
        cinemaDto.setId(this.id);
        return cinemaDto;
    }
}
