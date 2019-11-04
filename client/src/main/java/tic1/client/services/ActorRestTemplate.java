package tic1.client.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Actor;
import tic1.commons.transfers.MovieActorDTO;

import java.util.List;

public class ActorRestTemplate {
    public Actor showActor(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MovieActorDTO> response = restTemplate.exchange(
                "http://localhost:8080/actor/" + id, HttpMethod.GET, null, MovieActorDTO.class);
        MovieActorDTO actor = response.getBody();
        return new Actor(actor);
    }
}
