package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Provider;
import tic1.server.persistence.ProviderRepository;

import java.util.List;

@Service
public class ProviderMgr {
@Autowired
    ProviderRepository providerRepository;


    public Provider getById(long id){
        return providerRepository.getOne(id);
    }

    public void addProvider(Provider provider){
        providerRepository.save(provider);
    }

    public void deleteProvider(Provider provider){
        providerRepository.delete(provider);
    }

    public void updateProvidr(Long id, Provider provider){
        Provider  existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala", "id", id));
        existingProvider.setName(provider.getName());
        providerRepository.save(existingProvider);

    }
    public List<Provider> getByName(String name){
        return  providerRepository.findAllByName(name);
    };

}
