package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return salaRepository.getOne(id);
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
    public void delateById(long id){
        salaRepository.deleteById(id);
    }


}

