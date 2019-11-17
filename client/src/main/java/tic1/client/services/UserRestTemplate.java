package tic1.client.services;

import org.springframework.stereotype.Service;

@Service
public class UserRestTemplate {
    /*public  User showActor(long id){
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


    public void deleteGenre(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/genre/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }


    public void createActor(Actor actor) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<MovieActorDTO> body = new HttpEntity<>(
                actor.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/actor", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
*/
}
