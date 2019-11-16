package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Cinema;
import tic1.server.entities.Provider;
import tic1.server.persistence.CinemaRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class CinemaMgr {
    @Autowired
    CinemaRepository cinemaRepository;


    public List<Cinema> findAll(){ return cinemaRepository.findAll(); }

    public  void save(Cinema cinema){
        cinemaRepository.save(cinema);

    }
    public Cinema getOne(@PathVariable("id") Long id){
        return cinemaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }
    public void updateCinema(@PathVariable("id") Long id, @Valid @RequestBody Cinema tempCinema){
        Cinema existingCinema= cinemaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingCinema.setLocation(tempCinema.getLocation());
        existingCinema.setName(tempCinema.getName());
        existingCinema.setProvider(tempCinema.getProvider());
        cinemaRepository.save(existingCinema);
    }

    public ResponseEntity<?> deleteCinema(@PathVariable("id") Long id) {

         Cinema cinema= cinemaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        cinemaRepository.delete(cinema);

        return ResponseEntity.ok().build();
    }




    public List<Cinema> getByName(String name){
        return  cinemaRepository.findByName(name);
    };

    public  List<Cinema> findAllByProvider(Provider provider) {
        return cinemaRepository.findAllByProvider(provider);
    }



    public List<Cinema> getByProviderId(long providerId){
        return cinemaRepository.findAllByProviderId(providerId);
    }


    public List<Cinema> getByProviderName(String  providerName){
     return cinemaRepository.findAllByProviderName(providerName);
    }
}
