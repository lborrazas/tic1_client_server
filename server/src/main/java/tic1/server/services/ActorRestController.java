package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.MovieActorDTO;
import tic1.server.business.ActorsMgr;
import tic1.server.entities.Actor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ActorRestController {
    @Autowired
    private ActorsMgr actorsMgr;

    @GetMapping("/actor")
    public List<MovieActorDTO> genres() {
        List<Actor> genres = actorsMgr.findAll(); //todo List must be pages not full
        return genres.stream()
                .map(Actor::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/actor/{id}")
    public MovieActorDTO actor(@PathVariable("id") long id) {
        Actor actor = actorsMgr.findById(id).get(0);
        return actor.toDTO();
    }


    @PostMapping("/actor")
    public void save(@RequestBody MovieActorDTO genreDto){
        Actor actor = new Actor(genreDto);
        actorsMgr.save(actor);
    }

    @DeleteMapping("/actor/{id}")
    public void delete(@PathVariable("id") long id){
        actorsMgr.deleteById(id);
    }
}


