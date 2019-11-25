package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Cinema;
import tic1.server.entities.Provider;
import tic1.server.persistence.ProviderRepository;

import java.util.List;

@Service
public class ProviderMgr {
@Autowired
    ProviderRepository providerRepository;
@Autowired
    CinemaMgr cinemaMgr;

    public void  save(Provider s){
        providerRepository.save(s);
    }




    public Provider getById(long id){
        return providerRepository.findById(id).get();
    }

    public  List<Provider> getByName(String name){
        return providerRepository.findAllByName(name);

    }
    public void addProvider(Provider provider){
        providerRepository.save(provider);
    }

    public ResponseEntity<?> dalete(@PathVariable("id") Long id) {

        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));



        for (Cinema cine :cinemaMgr.getByProviderId(provider.getId())){
            cinemaMgr.deleteCinema(cine.getId());
        }
        providerRepository.delete(provider);




        return ResponseEntity.ok().build();
    }

    public void updateProvidr(Long id, Provider provider){
        Provider  existingProvider = providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala", "id", id));
        existingProvider.setName(provider.getName());
        providerRepository.save(existingProvider);
    }

    public List<Provider> returnAll(){
        return providerRepository.findAll();
    }

}
