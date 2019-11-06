package tic1.client.models;

import tic1.commons.transfers.MovieActorDTO;

public class Actor {
    private long id;
    private String name;
    private int year;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Actor() {
    }

    public Actor(MovieActorDTO temp){
        this.year = temp.getYear();
        this.id = temp.getId();
        this.name = temp.getName();
    }

    public MovieActorDTO toDTO(){
        MovieActorDTO temp = new MovieActorDTO();
        temp.setYear(this.year);
        temp.setId(this.id);
        temp.setName(this.name);
        return temp;
    }
}
