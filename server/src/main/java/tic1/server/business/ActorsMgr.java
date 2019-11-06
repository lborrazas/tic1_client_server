package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tic1.server.entities.Actor;
import tic1.server.persistence.ActorsRepository;

import java.util.List;

@Service
public class ActorsMgr {

    @Autowired
    private ActorsRepository actorsRepository;

    public List<Actor> findAll() {
        return actorsRepository.findAll();
    }

    public void addMovie(Actor actor) {

        actorsRepository.save(actor);

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
