package tic1.client.seeders;

import tic1.client.models.*;
import tic1.client.services.FuncionRestTemplate;
import tic1.client.services.SalaRestTemplate;
import tic1.client.services.TransaccionRestTemplate;
import tic1.client.services.UserRestTemplate;
import tic1.commons.transfers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;

public class TransaccionSeeder {

    public static void main(String[] args){
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setMaxfila(1);
        salaDTO.setMaxcolum(1);
        salaDTO.setName("1");
        Sala sala= new Sala(salaDTO);
        SalaRestTemplate salaRestTemplate = new SalaRestTemplate();
        salaRestTemplate.createSala(sala);
        MovieActorDTO actorDTO= new MovieActorDTO();
        actorDTO.setYear(1111);
        actorDTO.setId(1);
        actorDTO.setName("aa");
        LocalDate dia = LocalDate.of(2000,1,1);
        LocalTime hora = LocalTime.of(1,1,1);
        LocalDateTime fecahrandom =  LocalDateTime.of(dia,hora);
        NewMovieDTO movieDTO = new NewMovieDTO();
        movieDTO.setId(1);
        //Set<Actor> elenco = new Set<>();
//        movieDTO.setActors();
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setCinemaId(1);
        functionDTO.setMovie(movieDTO);
        functionDTO.setSala(1);
        functionDTO.setSecondId(2);
        functionDTO.setStartTime(fecahrandom);
        Funcion funcion = new Funcion(functionDTO);
        TransaccionRestTemplate transaccionRestTemplate = new TransaccionRestTemplate();
        TransaccionDTO transaccionDTO= new TransaccionDTO();
        //transaccionDTO.setClient();
        UserDTO userDTO = new UserDTO();
        userDTO.setType("Client");
        userDTO.setUsername("juan");
        userDTO.setPassword("juan");

        UserRestTemplate userRestTemplate = new UserRestTemplate();
        userRestTemplate.createActor(userDTO);

            Transaccion transaccion = new Transaccion(transaccionDTO);
        Seat seat = new Seat();
        seat.setColumna(1);
        seat.setFila(1);
        Sala sala1= new Sala();
        sala.setName("aa");
        seat.setSala(sala);


        funcion.setDate(fecahrandom);
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setFuncion_id(functionDTO);

        ticketDTO.setSeat(seat.toDTO());
        Ticket ticket = new Ticket(ticketDTO);

        ArrayList<Ticket> mandar = new ArrayList<>();
        mandar.add(ticket);
        FuncionRestTemplate funcionRestTemplate = new FuncionRestTemplate();
        funcionRestTemplate.save(funcion);
        transaccionRestTemplate.create(transaccion,mandar);




    }
}
