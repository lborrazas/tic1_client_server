package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.FunctionDTO;
import tic1.commons.transfers.SeatDTO;
import tic1.commons.transfers.TicketDTO;
import tic1.commons.transfers.TransaccionDTO;
import tic1.server.business.*;
import tic1.server.entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
          ticket.setTransaccion(transaccionMgr.getOne(ticketDTO.getTransaccionId())); // IMPORTANTE QUE PASA CUNADO NO LOS COMPRARON ??? NO HAH YRANSACION EL GET ONE TE DEVUELCE NULL?? NDEAH
       }ticket.setPrice(ticketDTO.getPrice());
        ticket.setDiscount(ticketDTO.getDiscount());
        ticket.setBought(ticketDTO.isBought());
        ticket.setId(ticketPk);

        return ticket;
    }


    @PostMapping("/ticket")
    public void save(@RequestBody TicketDTO ticketDTO) {
        ticketMgr.save(ticketFromDto(ticketDTO));
    }

    @DeleteMapping("/ticket/{id}")
    public void delete(@PathVariable TicketDTO ticketDTO) {
        ticketMgr.deleteTicket(ticketFromDto(ticketDTO).getId());
    }

    @GetMapping("/ticket/{id}")
    public TicketDTO getOne(@PathVariable TicketPk ticketPk) {
        return ticketMgr.getOne(ticketPk).toDTO();
    }

    @GetMapping("/ticket/after/{date}")
    List<TicketDTO> getAfterToday(@PathVariable LocalDateTime date) {

        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : ticketMgr.getByFuncionAfterDate(date)) {
            ticketDTOS.add(ticket.toDTO());
        }
        return ticketDTOS;

    }

    @GetMapping("/ticket/{sala_id}/{fecha}")
    public List<TicketDTO> getByFuncionId(@PathVariable("sala_id") long sala_id, @PathVariable("fecha")String fecha) {

        LocalDateTime dateTime =LocalDateTime.parse(fecha);
        List<Ticket> tickets = ticketMgr.findAllByFuncionId(dateTime, sala_id);
        return tickets.stream().map(Ticket::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/ticket/{sala_id}")
    public List<TicketDTO> getBySala(@PathVariable("sala_id") long sala_id) {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : ticketMgr.getBySala(salaMgr.getSalaById(sala_id))) {
            ticketDTOS.add(ticket.toDTO());
        }
        return ticketDTOS;
    }

    @PostMapping("/ticket/posCompra")
    public void updateFuncion(@RequestBody List<TicketDTO> ticketDTOs) {
        for (TicketDTO ticketDTO:ticketDTOs) {
            ticketMgr.updateTicket(ticketFromDto(ticketDTO).getId(),ticketFromDto(ticketDTO));
        }
    }


    @GetMapping("/ticket/{date}")
    public List<TicketDTO> getBydate(@PathVariable LocalDate date) {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : ticketMgr.getByOnDate(date)) {
            ticketDTOS.add(ticket.toDTO());
        }
        return ticketDTOS;
    }


    @GetMapping("/ticket/transaction/{transacionid}")
    public List<TicketDTO> getByTransacion(@PathVariable long transacionid) {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : ticketMgr.getByTransaccion(transaccionMgr.getOne(transacionid))) {
            ticketDTOS.add(ticket.toDTO());
        }
        return ticketDTOS;
    }

    @GetMapping("/ticket/client/{clientid}")
    public List<TicketDTO> getByClientId(@PathVariable long clientid) {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : ticketMgr.getByClientId(clientid)) {
            ticketDTOS.add(ticket.toDTO());
        }
        return ticketDTOS;
    }

    @GetMapping("/ticket/client/{clientname}")
    public List<TicketDTO> getBySala(@PathVariable String clientname) {
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket ticket : ticketMgr.getByClientUsername(clientname)) {
            ticketDTOS.add(ticket.toDTO());
        }
        return ticketDTOS;
    }
}
