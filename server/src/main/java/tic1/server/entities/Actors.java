package tic1.server.entities;

import tic1.commons.transfers.ActorsDto;

import javax.persistence.*;

@Entity
@Table
public class Actors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column
    private String name;
    @Column
    private int edad;

    public Actors() {
    }

    public Actors(ActorsDto actorsDto){
        this.edad = actorsDto.getEdad();
        this.id =actorsDto.getId();
        this.name=actorsDto.getName();

    }

    public Actors(long id, String name, int edad) {
        this.id = id;
        this.name = name;
        edad = edad;
    }

    public ActorsDto toDto()
 {
        ActorsDto actorsDto = new ActorsDto();
        actorsDto.setEdad(this.edad);
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        edad = edad;
    }
}
