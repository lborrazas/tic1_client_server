package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(Funcion funcion) {

        funcionRepository.save(funcion);

        for (int n = 1; n <= salaRepository.findById(funcion.getId().getSala().getId()).get().getMaxColumn(); n++) {
            for (int m = 1; m <= salaRepository.findById(funcion.getId().getSala().getId()).get().getMaxFila(); m++) {
                SeatPk seatPk = new SeatPk();
                seatPk.setSala(salaRepository.findById(funcion.getId().getSala().getId()).get());
                seatPk.setFila(m);
                seatPk.setColumna(n);
                Seat seat = seatRepository.findById(seatPk).get();

                Ticket ticket = new Ticket();
                TicketPk pk= new TicketPk();
                pk.setSeat(seat);
                pk.setFuncion(funcion);
                ticket.setId(pk);
                ticket.setBought(false);
                ticket.setLock(false);
                ticket.setPrice(150);
                ticketRepository.save(ticket);
            }
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

    ;//cuando se llame la funcion usar now()

}
