package tic1.server.entities;

//import tic1.commons.transfers.ActorsDto;

import tic1.commons.transfers.MovieActorDTO;

import javax.persistence.*;

@Entity
@Table
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    @Column
    private String name;
    @Column
    private int year;

    public Actor() {
    }



    public Actor(long id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Actor(MovieActorDTO tempDTO){
        this.year = tempDTO.getYear();
        this.id = tempDTO.getId();
        this.name = tempDTO.getName();
    }

    public MovieActorDTO toDTO()
 {
        MovieActorDTO actorsDto = new MovieActorDTO();
        actorsDto.setYear(this.year);
        actorsDto.setId(this.id);
        actorsDto.setName(this.name);
        return actorsDto;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
