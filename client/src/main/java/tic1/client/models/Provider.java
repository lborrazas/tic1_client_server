package tic1.client.models;

import tic1.commons.transfers.ProviderDTO;

public class Provider {

    private long id;

    private String name;

    public Provider(ProviderDTO providerDTO){
        this.name=providerDTO.getName();
        this.id=providerDTO.getId();
    }

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
        providerDTO.setName(this.name);
        providerDTO.setId(this.id);
        return  providerDTO;
    }
}
