package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.*;
import tic1.server.persistence.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FunctionMgr {
    @Autowired
    private FunctionRepository funcionRepository;
    // @Autowired
    // private MovieRepository movieRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private SeatRepository seatRepository;

    public void save(Funcion funcion, long precio) {

        funcionRepository.save(funcion);

        long salaId = funcion.getId().getSala().getId();
        Sala sala = salaRepository.findById(salaId).get();
        List<Seat> seats = sala.getSeats();
        List<Ticket> tickets = seats.stream().map(seat -> {
            Ticket ticket = new Ticket();
            TicketPk pk = new TicketPk();
            pk.setFuncion(funcion);
            pk.setSeat(seat);
            ticket.setId(pk);
            ticket.setPrice(precio);
            return ticket;
        }).collect(Collectors.toList());

        for (Ticket ticket : tickets) {
            ticketRepository.save(ticket);
        }
    }

    public Funcion getFunctionByPk(Sala sala, LocalDateTime localDateTime) {
        FunctionPK functionPK = new FunctionPK(sala, localDateTime);
        return funcionRepository.findById(functionPK).get();
    }

    public List<Funcion> findAll() {
        return funcionRepository.findAll();
    }


    public ResponseEntity<?> deleteFunction(@PathVariable("id") FunctionPK id) {

        Funcion funcion = funcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        funcionRepository.delete(funcion);

        return ResponseEntity.ok().build();
    }

    public List<Funcion> findByProvider(long id){
        return funcionRepository.findByProvider(id);
    }

    public List<Funcion> findByProviderPaged(long id, int page) {
        Pageable pageable = PageRequest.of(page, 9);
        Page<Funcion> movies = funcionRepository.findByProviderPaged(id, pageable);
        return movies.getContent();
    }


    public void updatefuncion(@PathVariable("id") FunctionPK id, @Valid @RequestBody Funcion tempFuncion) {
        Funcion existingFuncion = funcionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingFuncion.setMovie(tempFuncion.getMovie());
        funcionRepository.save(existingFuncion);
    }

    public List<Funcion> getByIdSala(Sala sala) {
        return funcionRepository.findAllByIdSala(sala);
    }

    ;


    public List<Funcion> fetchByIdSalaId(long salaid) {
        return funcionRepository.findAllByIdSalaId(salaid);
    }

    public List<Funcion> fetchByMovieAndIdDateAfter(Movie movie, LocalDateTime today) {
        return funcionRepository.findAllByMovieAndIdDateAfter(movie, today);
    }

    ;//cuando se llame la funcion usar now()

    public List<Funcion> getByDateAfter(LocalDateTime today) {
        return funcionRepository.findAllByIdDateAfter(today);
    }

    ;//cuando se llame la funcion usar now()


    public List<Funcion> getByMovieAndDate(Movie movie, LocalDateTime today) {
        return funcionRepository.findAllByMovieAndIdDateAfter(movie, today);//cuando se llame la funcion usar now()
    }

    public List<Funcion> getByMovie(Movie movie) {
        return funcionRepository.findAllByMovie(movie);
    }

    ;//cuando se llame la funcion usar now()

}
