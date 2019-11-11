package tic1.client.services;

import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import tic1.client.models.Actor;
import tic1.client.models.Genre;
import tic1.client.models.Movie;
import tic1.commons.transfers.NewMovieDTO;
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
        HttpEntity<NewMovieDTO> body = new HttpEntity<>(
                movie.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/movie", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public void updateMovie(long id, Movie movie) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<NewMovieDTO> body = new HttpEntity<>(
                movie.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/movie/"+id, HttpMethod.PUT, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public void deleteMovie(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/movie/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public Movie showMovie(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NewMovieDTO> response = restTemplate.exchange(
                "http://localhost:8080/movie/" + id, HttpMethod.GET, null, NewMovieDTO.class);
        NewMovieDTO movie = response.getBody();
        return new Movie(movie);
    }

    @Deprecated
    public List<Movie> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<NewMovieDTO>> response = restTemplate.exchange(
                "http://localhost:8080/movie",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NewMovieDTO>>(){});
        List<NewMovieDTO> movies = response.getBody();
        return movies.stream()
                .map(Movie::new)
               // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }


    public List<Movie> findAllPaged(int page){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<NewMovieDTO>> response = restTemplate.exchange(
                "http://localhost:8080/movie/paged/" + page,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NewMovieDTO>>(){});
        List<NewMovieDTO> movies = response.getBody();
        return movies.stream()
                .map(Movie::new)
                // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }

    public List<Movie> filterGenrePaged(Genre genre, int page){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<NewMovieDTO>> response = restTemplate.exchange(
                "http://localhost:8080/movie/genre/"+genre.getId()+'/'+page,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NewMovieDTO>>(){});
        List<NewMovieDTO> movies = response.getBody();
        return movies.stream()
                .map(Movie::new)
                // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }

    public List<Movie> filterActorPaged(Actor actor, int page){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<NewMovieDTO>> response = restTemplate.exchange(
                "http://localhost:8080/movie/actor/"+actor.getId()+'/'+page,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NewMovieDTO>>(){});
        List<NewMovieDTO> movies = response.getBody();
        return movies.stream()
                .map(Movie::new)
                // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }

    public List<Movie> filterTitlePaged(String title, int page){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<NewMovieDTO>> response = restTemplate.exchange(
                "http://localhost:8080/movie/title/" + title+ "/" +page,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NewMovieDTO>>(){});
        List<NewMovieDTO> movies = response.getBody();
        return movies.stream()
                .map(Movie::new)
                // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }
}

