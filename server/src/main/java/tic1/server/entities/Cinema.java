package tic1.server.entities;

import tic1.commons.transfers.CinemaDto;

import javax.persistence.*;

@Entity
@Table
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private String location;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Provider provider;




    public Cinema() {
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public CinemaDto toDTO() {
        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setId(this.id);
        cinemaDto.setLocation(this.location);
        cinemaDto.setName(this.name);
        cinemaDto.setProvider(this.provider.getId());
        return cinemaDto;
    }
}



