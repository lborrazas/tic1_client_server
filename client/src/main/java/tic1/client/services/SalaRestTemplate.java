package tic1.client.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Sala;
import tic1.client.models.Ticket;
import tic1.commons.transfers.SalaDTO;
import tic1.commons.transfers.TicketDTO;

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

    //falta delate y get
}
