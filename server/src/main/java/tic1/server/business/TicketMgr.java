package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.*;
import tic1.server.persistence.SalaRepository;
import tic1.server.persistence.TicketRepository;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TicketMgr {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    SalaRepository salaRepository;

    public List<Ticket> findAll(){ return ticketRepository.findAll(); }

    public  void save(Ticket ticket){
        ticketRepository.save(ticket);

    }
    public Ticket getOne(@PathVariable("id") TicketPk id){
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }
    public void updateTicket(@PathVariable("id") TicketPk id, @Valid @RequestBody Ticket ticket){
        Ticket existingTicket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingTicket.setBought(ticket.getIsBought());                                                                  //falta el catcheo de exepciones ("SKERE")
        existingTicket.setDiscount(ticket.getDiscount());
        existingTicket.setPrice(ticket.getPrice());
        existingTicket.setTransaccion(ticket.getTransaccion());
        existingTicket.setLock(ticket.isLock());
        ticketRepository.save(existingTicket);
    }

    public ResponseEntity<?> deleteTicket(@PathVariable("id") TicketPk id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        ticketRepository.delete(ticket);

        return ResponseEntity.ok().build();
    }
public     List<Ticket> getByFuncionAfterDate(LocalDateTime today){
     return    ticketRepository.findAllByIdFuncionIdDate(today);
    };

    public List<Ticket> getBySala(Sala sala){
        return  ticketRepository.findAllByIdFuncionIdSala(sala);
    };

    public List<Ticket> findAllByIdFuncionIdSalaId(Long idsala){
        return  ticketRepository.findAllByIdFuncionIdSalaId(idsala);
    };

    public List<Ticket> findAllByFuncionId(LocalDateTime fecha,long sala_id){
        FunctionPK functionPK =new FunctionPK();
        functionPK.setSala(salaRepository.findById(sala_id).get());
        functionPK.setDate(fecha);


        return ticketRepository.findAllByIdFuncionId(functionPK);

    }

   public List<Ticket> getByTransaccion(Transaccion transaccion){
    return    ticketRepository.findAllByTransaccion(transaccion);
   }
    public List<Ticket> getByClient(User client){
    return  ticketRepository.findAllByTransaccionClient(client);
    }

    public List<Ticket> getByClientId(long id){
       return  ticketRepository.findAllByTransaccionClientId(id);
    }
    public List<Ticket> getByClientUsername(String username){
       return  ticketRepository.findAllByTransaccionClientUsername(username);
    }

    public List<Ticket> getByOnDate(LocalDate today){

        LocalDate fecha = today;
        LocalTime dawn =  LocalTime.MAX;//23:59:999999
        LocalTime night =  LocalTime.MIN;//00:00
       return ticketRepository.findAllByIdFuncionIdDateBetween(LocalDateTime.of( fecha,  night),LocalDateTime.of( fecha,  dawn));
    };

}
