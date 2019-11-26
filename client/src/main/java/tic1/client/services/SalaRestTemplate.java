package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Cinema;
import tic1.client.models.Sala;
import tic1.client.models.Ticket;
import tic1.commons.transfers.CinemaDto;
import tic1.commons.transfers.SalaDTO;
import tic1.commons.transfers.TicketDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SalaRestTemplate {

    public void createSala(Sala sala) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<SalaDTO> body = new HttpEntity<>(
                sala.toDTO());
        SalaDTO a = sala.toDTO();

        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/sala"+"/"+a.getMaxcolum()+"/"+a.getMaxfila(), HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
    public List<Sala> returAll(){
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<List<SalaDTO>> response =
                restTemplate.exchange("http://localhost:8080/sala/", HttpMethod.GET, null,new ParameterizedTypeReference<List<SalaDTO>>(){});
        System.out.println("RestTemplate response : " + response.getBody());

        List<SalaDTO> salas = response.getBody();
        return salas.stream()
                .map(Sala::new)
                .collect(Collectors.toList());
    }

    public List<Sala> getByCinemaId(long cinemaId){
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<List<SalaDTO>> response =
                restTemplate.exchange("http://localhost:8080/sala/cinema/" + cinemaId, HttpMethod.GET, null,new ParameterizedTypeReference<List<SalaDTO>>(){});
        System.out.println("RestTemplate response : " + response.getBody());

        List<SalaDTO> salas = response.getBody();
        return salas.stream()
                .map(Sala::new)
                .collect(Collectors.toList());
    }

    public  Sala getById(long id){
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<SalaDTO> response =
                restTemplate.exchange("http://localhost:8080/sala/"+id, HttpMethod.GET, null,new ParameterizedTypeReference<SalaDTO>(){});
        System.out.println("RestTemplate response : " + response.getBody());


        return new Sala(Objects.requireNonNull(response.getBody())); }

    //falta delate y get
}
