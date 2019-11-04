package tic1.client.models;

import tic1.commons.transfers.MovieActorDTO;

public class Actor {
    private long id;
    private String name;
    private int age;

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

    public Actor() {
    }

    public Actor(MovieActorDTO temp){
        this.age = temp.getAge();
        this.id = temp.getId();
        this.name = temp.getName();
    }

    public MovieActorDTO toDTO(){
        MovieActorDTO temp = new MovieActorDTO();
        temp.setAge(this.age);
        temp.setId(this.id);
        temp.setName(this.name);
        return temp;
    }
}
