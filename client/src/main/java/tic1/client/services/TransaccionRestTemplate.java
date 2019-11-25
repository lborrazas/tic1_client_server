package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Actor;
import tic1.client.models.Ticket;
import tic1.client.models.Transaccion;
import tic1.commons.transfers.MovieActorDTO;
import tic1.commons.transfers.Nodo;
import tic1.commons.transfers.TicketDTO;
import tic1.commons.transfers.TransaccionDTO;

import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransaccionRestTemplate {
    public Transaccion showActor(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TransaccionDTO> response = restTemplate.exchange(
                "http://localhost:8080/transaccion/" + id, HttpMethod.GET, null, TransaccionDTO.class);
        TransaccionDTO transaccionDTO = response.getBody();
        return new Transaccion(transaccionDTO);
    }


    @Deprecated
    public List<Transaccion> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<TransaccionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/transaccion",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TransaccionDTO>>(){});
        List<TransaccionDTO> transaccionDTOS = response.getBody();
        return transaccionDTOS.stream()
                .map(Transaccion::new)
                .collect(Collectors.toList());
    }


    public void deleteTransaccion(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/transaccion/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
    @Deprecated
    public List<Transaccion> findByClient(long clientID){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<TransaccionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/transaccion"+clientID,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TransaccionDTO>>(){});
        List<TransaccionDTO> transaccionDTOS = response.getBody();
        return transaccionDTOS.stream()
                .map(Transaccion::new)
                .collect(Collectors.toList());
    }
    @Deprecated
    public List<Transaccion> findByClientName(String name){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<TransaccionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/transaccion"+name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TransaccionDTO>>(){});
        List<TransaccionDTO> transaccionDTOS = response.getBody();
        return transaccionDTOS.stream()
                .map(Transaccion::new)
                .collect(Collectors.toList());
    }

    public void create(Transaccion transaccion, List<Ticket> tickets) { // aca mate
        ArrayList<TicketDTO> DTOS = new ArrayList<>();
        for (Ticket ticket:tickets){
            DTOS.add(ticket.toDTO()); // aca hay unr prolbe a von los tickets
        }
        Nodo skere = new Nodo();

        skere.setTransaccionDTO(transaccion.toDTO());
        skere.setTicketDTOS( DTOS);
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<Nodo> body = new HttpEntity<>(skere);
        ResponseEntity<String> response =
                restTemplate.   exchange("http://localhost:8080/transaccion", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
}
