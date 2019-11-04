package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.MovieActorDTO;
import tic1.server.entities.Actor;
import tic1.server.persistence.ActorsRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ActorRestController {
    @Autowired
    private ActorsRepository actorsRepository;

    @GetMapping("/actor")
    public List<MovieActorDTO> genres() {
        List<Actor> genres = actorsRepository.findAll(); //todo List must be pages not full
        return genres.stream()
                .map(Actor::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/actor/{id}")
    public MovieActorDTO actor(@PathVariable long id) {
        Actor actor = actorsRepository.findById(id).get();
        return actor.toDTO();
    }


    @PostMapping("/actor")
    public void save(@RequestBody MovieActorDTO genreDto){
        Actor actor = new Actor(genreDto);
        actorsRepository.save(actor);
    }

    @DeleteMapping("/actor/{id}")
    public void delete(@PathVariable long id){
        actorsRepository.deleteById(id);
    }
}


