package tic1.client.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Genre;
import tic1.commons.transfers.MovieGenreDTO;

@Service
public class GenreRestTemplate {


    public Genre showGenre(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MovieGenreDTO> response = restTemplate.exchange(
                "http://localhost:8080/genre/" + id, HttpMethod.GET, null, MovieGenreDTO.class);
        MovieGenreDTO genre = response.getBody();
        return new Genre(genre);
    }

}
