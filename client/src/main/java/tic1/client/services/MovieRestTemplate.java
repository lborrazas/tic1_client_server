package tic1.client.services;

import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import tic1.client.models.Movie;
import tic1.commons.transfers.MovieDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieRestTemplate { //todo try and catch for Templates

    public void createMovie(Movie movie) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<MovieDTO> body = new HttpEntity<>(
                movie.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/movie", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public void updateMovie(Movie movie) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<MovieDTO> body = new HttpEntity<>(
                new MovieDTO(movie.getDescription(), movie.getDuration(), movie.getActors(), movie.getName()));
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/movie", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public List<Movie> findAll(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<MovieDTO>> response = restTemplate.exchange(
                "http://localhost:8080/movie",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieDTO>>(){});
        List<MovieDTO> movies = response.getBody();
        return movies.stream()
                .map(Movie::new)
               // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }



    public void deleteMovie(){

    }
}

