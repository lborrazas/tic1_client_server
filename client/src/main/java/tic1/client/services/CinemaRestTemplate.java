package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Cinema;
import tic1.client.models.Provider;
import tic1.commons.transfers.CinemaDto;
import tic1.commons.transfers.ProviderDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaRestTemplate {

    public List<Cinema> get() {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<List<CinemaDto>> response =
                restTemplate.exchange("http://localhost:8080/cinema/", HttpMethod.GET, null, new ParameterizedTypeReference<List<CinemaDto>>() {
                });
        System.out.println("RestTemplate response : " + response.getBody());

        List<CinemaDto> cines = response.getBody();
        return cines.stream()
                .map(Cinema::new)
                .collect(Collectors.toList());

    }

    public void createCinema(Cinema cinema) {
        RestTemplate restTemplate =
                new RestTemplate();
        CinemaDto pepe = cinema.toDTO();
        HttpEntity<CinemaDto> body = new HttpEntity<>(
                pepe);

        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/cinema/" + pepe.getProviderId() + "/", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public List<Cinema> getByProvider(long providerId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CinemaDto>> response =
                restTemplate.exchange("http://localhost:8080/cinema/provider/" + providerId, HttpMethod.GET, null, new ParameterizedTypeReference<List<CinemaDto>>() {
                });
        System.out.println("RestTemplate response : " + response.getBody());

        List<CinemaDto> cines = response.getBody();
        return cines.stream()
                .map(Cinema::new)
                .collect(Collectors.toList());

    }

    public Cinema getOne(long cinemaId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CinemaDto> response =
                restTemplate.exchange("http://localhost:8080/cinema/id/" + cinemaId, HttpMethod.GET, null, new ParameterizedTypeReference<CinemaDto>() {
                });
        System.out.println("RestTemplate response : " + response.getBody());

        CinemaDto cine = response.getBody();

        return new Cinema(cine);
    }
}

