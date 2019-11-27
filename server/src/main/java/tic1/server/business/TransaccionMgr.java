package tic1.server.business;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.commons.transfers.FunctionDTO;
import tic1.commons.transfers.SeatDTO;
import tic1.commons.transfers.TicketDTO;
import tic1.server.entities.*;
import tic1.server.persistence.TicketRepository;
import tic1.server.persistence.TransaccionRepository;
import tic1.server.persistence.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransaccionMgr {

    public Funcion funtionFromDto(FunctionDTO functionDTO) {
        Funcion funcion = new Funcion();
        FunctionPK functionPK = new FunctionPK();

        functionPK.setDate(functionDTO.getStartTime());
        functionPK.setSala(salaMgr.getSalaById(functionDTO.getSala()));

        funcion.setMovie(movieMgr.getOne(functionDTO.getMovie().getId()));
        funcion.setId(functionPK);
        return funcion;
    }

    public Seat seatFromDto(SeatDTO seatDTO) {
        SeatPk seatPk = new SeatPk();
        seatPk.setColumna(seatDTO.getColumn());
        seatPk.setFila(seatDTO.getRow());
        seatPk.setSala(salaMgr.getSalaById(seatDTO.getSala_id()));

        Seat seat = new Seat();
        seat.setId(seatPk);

        return seat;
    }


    private Ticket ticketFromDto(TicketDTO ticketDTO) {
        TicketPk ticketPk = new TicketPk();


        ticketPk.setFuncion(funtionFromDto(ticketDTO.getFuncion_id()));
        ticketPk.setSeat(seatFromDto(ticketDTO.getSeat()));

        Ticket ticket = new Ticket();
        ticket.setLock(ticketDTO.isLock());

        if(ticketDTO.getTransaccionId() != 0) {
            ticket.setTransaccion(this.getOne(ticketDTO.getTransaccionId())); // IMPORTANTE QUE PASA CUNADO NO LOS COMPRARON ??? NO HAH YRANSACION EL GET ONE TE DEVUELCE NULL?? NDEAH
        }ticket.setPrice(ticketDTO.getPrice());
        ticket.setDiscount(ticketDTO.getDiscount());
        ticket.setBought(ticketDTO.isBought());
        ticket.setId(ticketPk);

        return ticket;
    }
    @Autowired
    private  TransaccionRepository transaccionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private MovieMgr movieMgr;
    @Autowired
    private  SalaMgr salaMgr;
    @Autowired
    private  SeatMgr seatMgr;

    @GetMapping("/")
    public List<Transaccion> findAll() {
        return transaccionRepository.findAll();
    }

    public void addTransaccion(Transaccion transaccion) {


        transaccionRepository.save(transaccion);

    }

    public Transaccion getOne(@PathVariable("id") Long id) {
        return transaccionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

    }
    public ResponseEntity<?> deleteTransaccion(@PathVariable("id") Long id) {

        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        transaccionRepository.delete(transaccion);

        return ResponseEntity.ok().build();
    }


    public void updateTransaccion(@PathVariable("id") Long id, @Valid @RequestBody Transaccion transaccion) {

        Transaccion existingtransaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        existingtransaccion.setClient(transaccion.getClient());
        existingtransaccion.setPrecioTotal(transaccion.getPrecioTotal());
        transaccionRepository.save(existingtransaccion);
    }
    public List<Transaccion> getByClint(Client client){
        return  transaccionRepository.findAllByClient(client);
    }

    public  List<Transaccion> getByClintId(long id){
        return  transaccionRepository.findAllByClientId(id);}

    public  List<Transaccion> getByClintUsername(String username){
        return  transaccionRepository.findAllByClientUsername(username);
    }


    public void addTransaccion(List<TicketDTO> tickets, long clientid) {
        Transaccion transaccion= new Transaccion();
        transaccion.setClient(userRepository.findById(clientid).get());
        ArrayList<Ticket> tickets1 = new ArrayList<>();
            int precioTotal= 0;
        for (TicketDTO ticketDTO: tickets){
            precioTotal= (int) (precioTotal+ticketDTO.getPrice());
           Ticket ticket= ticketFromDto(ticketDTO);
           tickets1.add(ticket);
        }
        transaccion.setPrecioTotal(precioTotal);
        transaccionRepository.save(transaccion);
        for (Ticket ticket:tickets1){
            ticket.setTransaccion(transaccion);
            ticketRepository.save(ticket);
        }
    }
}
