package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Funcion;
import tic1.client.models.Movie;
import tic1.commons.transfers.FunctionDTO;
import tic1.commons.transfers.NewMovieDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FuncionRestTemplate {

    public Funcion show(long id) { // esto esta mal

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FunctionDTO> response = restTemplate.exchange(
                "http://localhost:8080//function/{id}" + id, HttpMethod.GET, null, FunctionDTO.class);
        FunctionDTO tempFuncion = response.getBody();
        return new Funcion(tempFuncion);

    }

    public List<Funcion> getByMovieIdAndDate(LocalDateTime fecha, Movie Movie) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FunctionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/function/" + fecha + '/' + Movie.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<FunctionDTO>>() {
                });
        List<FunctionDTO> functionDTOS = response.getBody();
        return functionDTOS.stream()
                .map(Funcion::new)
                // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }


    public List<Funcion> returnAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FunctionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/funcion", HttpMethod.GET, null, new ParameterizedTypeReference<List<FunctionDTO>>() {
                });
        List<FunctionDTO> functionDTOS = response.getBody();
        List<Funcion> funcions = new ArrayList<>();
        for (FunctionDTO functionDTO : functionDTOS) {
            funcions.add(new Funcion(functionDTO));

        }
        return funcions;
    }

    public void save(Funcion funcion) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<FunctionDTO> body = new HttpEntity<>(
                funcion.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/funcion/", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    @Deprecated
    public List<Funcion> findAllbyProvider(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FunctionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/funcion/provider/"+ id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FunctionDTO>>(){});
        List<FunctionDTO> functions = response.getBody();
        return functions.stream()
                .map(Funcion::new)
                // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }

    public List<Funcion> findAllByProviderIdPaged(long id, int page){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FunctionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/funcion/provider/"+ id +"/paged/" + page,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FunctionDTO>>(){});
        List<FunctionDTO> functions = response.getBody();
        return functions.stream()
                .map(Funcion::new)
                // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }

    public List<Funcion> getByMovieId(Movie movie) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FunctionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/function/movie/" + movie.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<FunctionDTO>>() {
                });
        List<FunctionDTO> functionDTOS = response.getBody();
        return functionDTOS.stream()
                .map(Funcion::new)
                // .map(movieDTO -> new Movie(movieDTO))
                .collect(Collectors.toList());
    }


    public void deleteFuncion( Funcion function) {
        Long salaId = function.getSalaId();
        LocalDateTime localDateTime = function.getDate();
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/function/"+ salaId  + "/" + localDateTime, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }




}
