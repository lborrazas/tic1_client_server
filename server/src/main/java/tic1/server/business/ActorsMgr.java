package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Actor;
import tic1.server.persistence.ActorsRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class ActorsMgr {

    @Autowired
    private ActorsRepository actorsRepository;

    public List<Actor> findAll() {
        return actorsRepository.findAll();
    }

    public void addActor(Actor actor) {

        actorsRepository.save(actor);

    }

    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id) {

        Actor actor= actorsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        actorsRepository.delete(actor);

        return ResponseEntity.ok().build();
    }

    public void updateActor(@PathVariable("id") Long id, @Valid @RequestBody Actor tempActor){
        Actor existingActor=actorsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingActor.setName(tempActor.getName());
        existingActor.setYear(tempActor.getYear());
        actorsRepository.save(existingActor);
    }



    public List<Actor> findById(long id) {
        return  actorsRepository.findAllById(id);
    }

    public List<Actor> findByName(String name) {
        return  actorsRepository.findAllByNameContaining(name);
    }


    public void save(Actor actor) {
        actorsRepository.save(actor);
    }

    public void deleteById(long id) {
        actorsRepository.deleteById(id);
    }

    public Actor getOne(Long actorId) {
        return actorsRepository.getOne(actorId);
    }
}
