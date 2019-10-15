package tic1.client.services;

import tic1.commons.transfers.MovieDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class MovieRestTemplate {
    RestTemplate restTemplate =
            new RestTemplate();
    HttpEntity<MovieDTO> body = new HttpEntity<>(
            new MovieDTO()); //todo im here
    ResponseEntity<String> response =
            restTemplate.exchange("http://10.252.254.8:8080/MovieDTO", HttpMethod.POST, body, String.class);

}

