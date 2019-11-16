package tic1.server.entities;

import tic1.commons.transfers.ProviderDTO;

import javax.persistence.*;

@Entity
@Table
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;

    public Provider() {
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

    public ProviderDTO toDTO() {
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setId(this.id);
        providerDTO.setName(this.name);
        return  providerDTO;
    }
}
