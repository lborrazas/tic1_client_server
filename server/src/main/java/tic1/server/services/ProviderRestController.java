package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.ProviderDTO;
import tic1.server.business.ProviderMgr;
import tic1.server.entities.Provider;

import java.util.ArrayList;
import java.util.List;

public class ProviderRestController {
    @Autowired
    ProviderMgr providerMgr;

    private Provider providerFromDto(ProviderDTO providerDTO){
        Provider provider = new Provider();
        provider.setName(providerDTO.getName());
        provider.setId(providerDTO.getId());
        return provider;
    }


    @PostMapping("/provider")
    public void save(@RequestBody ProviderDTO providerDTO){
        Provider provider = providerFromDto(providerDTO);

        providerMgr.save(provider);
    }

    @DeleteMapping("/provider/{id}")
    public void delete(@PathVariable long id){
        providerMgr.dalete(id);
    }

    @GetMapping("/provider/{id}")
    public  ProviderDTO getById(@RequestBody long id){
          return providerMgr.getById(id).toDTO();

    }
    @GetMapping("/provider/{name}")
    public List<ProviderDTO> getAllbyName(@RequestBody String name){
        List<ProviderDTO> providerDTOS = new ArrayList<>();
        for (Provider provider: providerMgr.getByName(name)){
            providerDTOS.add(provider.toDTO());
        }
        return providerDTOS;
    }

}
