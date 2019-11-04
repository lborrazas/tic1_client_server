package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Genre;
import tic1.commons.transfers.MovieGenreDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreRestTemplate {


    public Genre showGenre(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MovieGenreDTO> response = restTemplate.exchange(
                "http://localhost:8080/genre/" + id, HttpMethod.GET, null, MovieGenreDTO.class);
        MovieGenreDTO genre = response.getBody();
        return new Genre(genre);
    }

    @Deprecated
    public List<Genre> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<MovieGenreDTO>> response = restTemplate.exchange(
                "http://localhost:8080/genre",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieGenreDTO>>(){});
        List<MovieGenreDTO> movies = response.getBody();
        return movies.stream()
                .map(Genre::new)
                .collect(Collectors.toList());
    }

}
