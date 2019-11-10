package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.*;
import tic1.server.persistence.TicketRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketMgr {
    @Autowired
    TicketRepository ticketRepository;

    public List<Ticket> findAll(){ return ticketRepository.findAll(); }

    public  void save(Ticket ticket){
        ticketRepository.save(ticket);

    }
    public Ticket getOne(@PathVariable("id") TicketPk id){
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }
    public void updateTicket(@PathVariable("id") TicketPk id, @Valid @RequestBody Ticket ticket){
        Ticket existingTicket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingTicket.setBought(ticket.isBought());                                                                  //falta el catcheo de exepciones ("SKERE")
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

   public List<Ticket> getByTransaccion(Transaccion transaccion){
    return    ticketRepository.findAllByTransaccion(transaccion);
   }
    public List<Transaccion> getByClient(User client){
    return  ticketRepository.findAllByTransaccionClient(client);
    }

    public List<Transaccion> getByClientId(long id){
       return  ticketRepository.findAllByTransaccionClientId(id);
    }
    public List<Transaccion> getByClientUsername(String username){
       return  ticketRepository.findAllByTransaccionClientUsername(username);
    }

    public List<Ticket> getByDateAfter(LocalDateTime today){
       return ticketRepository.findAllByIdFuncionIdDateAfter(today);
    };

}
