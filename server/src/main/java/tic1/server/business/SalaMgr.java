package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Cinema;
import tic1.server.entities.Sala;
import tic1.server.persistence.SalaRepository;

import java.util.List;

@Service
public class SalaMgr {
@Autowired
    private SalaRepository salaRepository;

    public Sala getSalaById(long id){
        return salaRepository.findById(id).get();
    }

    public void addSala(Sala sala){
        salaRepository.save(sala);
    }

    public void deleteSala(Sala sala){
        salaRepository.delete(sala);
    }

    public void updateSala(Long id, Sala sala){
        Sala  existingSala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala", "id", id));
        existingSala.setCinema(sala.getCinema());
        salaRepository.save(existingSala);

    }

    public  List<Sala> getByName(String name){
        return salaRepository.findAllByName(name);
    };
    public  List<Sala> findAllByCinema(Cinema cinema){
        return salaRepository.findAllByCinema(cinema);
    }


    public ResponseEntity<?> delateById(@PathVariable("id") Long id) {

        Sala user = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        salaRepository.delete(user);

        return ResponseEntity.ok().build();
    }


}

