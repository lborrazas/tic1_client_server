package tic1.server.entities;

//import tic1.commons.transfers.ActorsDto;

import tic1.commons.transfers.MovieActorDTO;

import javax.persistence.*;

@Entity
@Table
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column
    private String name;
    @Column
    private int age;

    public Actor() {
    }



    public Actor(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public MovieActorDTO toDTO()
 {
        MovieActorDTO actorsDto = new MovieActorDTO();
        actorsDto.setAge(this.age);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
