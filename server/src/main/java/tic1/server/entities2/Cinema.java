package tic1.server.entities2;

import tic1.server.entities.Proveedor;

import javax.persistence.*;

@Entity
@Table
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @Column
    public String name;
    @Column
    public String location;

    @ManyToOne
    @JoinColumn(name = "id_provider")
     public Provider provider;


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
}
