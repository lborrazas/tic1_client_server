package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Actor;
import tic1.commons.transfers.MovieActorDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ActorRestTemplate {
    public Actor showActor(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MovieActorDTO> response = restTemplate.exchange(
                "http://localhost:8080/actor/" + id, HttpMethod.GET, null, MovieActorDTO.class);
        MovieActorDTO actor = response.getBody();
        return new Actor(actor);
    }


    @Deprecated
    public List<Actor> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<MovieActorDTO>> response = restTemplate.exchange(
                "http://localhost:8080/actor",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieActorDTO>>(){});
        List<MovieActorDTO> movies = response.getBody();
        return movies.stream()
                .map(Actor::new)
                .collect(Collectors.toList());
    }
}
