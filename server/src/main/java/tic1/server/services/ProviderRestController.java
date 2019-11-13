package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.transfers.ProviderDTO;
import tic1.server.business.ProviderMgr;
import tic1.server.entities.Provider;

public class ProviderRestController {
    @Autowired
    ProviderMgr providerMgr;

    private Provider providerFromDto(ProviderDTO providerDTO){
        Provider provider = new Provider();
        provider.setName(providerDTO.getName());
        provider.setId(providerDTO.getId());
        return provider;
    }


    @PostMapping("/cinema")
    public void save(@RequestBody ProviderDTO providerDTO){
        Provider provider = providerFromDto(providerDTO);

        providerMgr.save(provider);
    }

    @DeleteMapping("/cinema/{id}")
    public void delete(@PathVariable long id){
        providerMgr.dalete(id);
    }


}
