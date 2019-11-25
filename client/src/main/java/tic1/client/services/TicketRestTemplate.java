package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Ticket;
import tic1.commons.transfers.TicketDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketRestTemplate {
    public Ticket showTicket(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TicketDTO> response = restTemplate.exchange(
                "http://localhost:8080/ticket/" + id, HttpMethod.GET, null, TicketDTO.class);
        TicketDTO ticketDTO = response.getBody();
        return new tic1.client.models.Ticket(ticketDTO);
    }


    @Deprecated
    public List<Ticket> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<TicketDTO>> response = restTemplate.exchange(
                "http://localhost:8080/ticket",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TicketDTO>>(){});
        List<TicketDTO> movies = response.getBody();
        return movies.stream()
                .map(Ticket::new)
                .collect(Collectors.toList());
    }

    public List<Ticket> findByFunction_dateAndsalaid(LocalDateTime fecha, long salaid){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<TicketDTO>> response = restTemplate.exchange(
                "http://localhost:8080/ticket/"+salaid+"/"+fecha,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TicketDTO>>(){});
        List<TicketDTO> tickets = response.getBody();
        return tickets.stream()
                .map(Ticket::new)
                .collect(Collectors.toList());
    }

    public void update(List<Ticket> tickets) {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket tickettemp:tickets){
            ticketDTOS.add(tickettemp.toDTO());
        }
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<List<TicketDTO>> body = new HttpEntity<>(
                ticketDTOS);
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/ticket/posCompra", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public void delateTicket(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/ticket/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }


    public void createTicket(Ticket ticket) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<TicketDTO> body = new HttpEntity<>(
                ticket.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/ticket", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
}
