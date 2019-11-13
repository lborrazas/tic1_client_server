package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.FunctionDTO;
import tic1.commons.transfers.SeatDTO;
import tic1.commons.transfers.TicketDTO;
import tic1.server.business.*;
import tic1.server.entities.*;

@RestController
public class TicketRestController {
@Autowired
    TicketMgr ticketMgr;
@Autowired
    FunctionMgr functionMgr;
@Autowired
    MovieMgr movieMgr;
@Autowired
    SalaMgr salaMgr;
@Autowired
    TransaccionMgr transaccionMgr;

 private Funcion funtionFromDto(FunctionDTO functionDTO){
     Funcion funcion = new Funcion();
     FunctionPK functionPK = new FunctionPK();

     functionPK.setDate(functionDTO.getStartTime());
     functionPK.setSala(salaMgr.getSalaById(functionDTO.getSala()));

     funcion.setSecondId(functionDTO.getSecondId());
     funcion.setMovie(movieMgr.getOne(functionDTO.getMovie().getId()));
     funcion.setId(functionPK);
    return  funcion;
 }
    private Seat seatFromDto(SeatDTO seatDTO) {
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


    ticketPk.setFuncion(funtionFromDto(ticketDTO.getFuncionId()));
    ticketPk.setSeat(seatFromDto(ticketDTO.getSeat()));

    Ticket ticket = new Ticket();
    ticket.setLock(ticketDTO.isLock());
    ticket.setTransaccion(transaccionMgr.getOne(ticketDTO.getTransaccion_id())); // IMPORTANTE QUE PASA CUNADO NO LOS COMPRARON ??? NO HAH YRANSACION EL GET ONE TE DEVUELCE NULL?? NDEAH
    ticket.setPrice(ticketDTO.getPrice());
    ticket.setDiscount(ticketDTO.getDiscount());
    ticket.setBought(ticketDTO.isBought());
    ticket.setId(ticketPk);

    return ticket;
}


    @PostMapping("/ticket")
    public void save(@RequestBody TicketDTO ticketDTO){
         ticketMgr.save(ticketFromDto(ticketDTO));
    }

    @DeleteMapping("/ticket/{id}")
    public void delete(@PathVariable TicketDTO ticketDTO){
     ticketMgr.deleteTicket(ticketFromDto(ticketDTO).getId());
    }

}
