package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.*;
import tic1.server.business.*;
import tic1.server.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransaccionRestController {
@Autowired
    TransaccionMgr transaccionMgr;
@Autowired
    UserMgr userMgr;

@Autowired
SalaMgr salaMgr;
@Autowired
MovieMgr movieMgr;
@Autowired
    TicketMgr ticketMgr;


    public Funcion funtionFromDto(FunctionDTO functionDTO) {
        Funcion funcion = new Funcion();
        FunctionPK functionPK = new FunctionPK();

        functionPK.setDate(functionDTO.getStartTime());
        functionPK.setSala( salaMgr.getSalaById(functionDTO.getSala()));


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


    public Transaccion transaccionFromDTO(TransaccionDTO transaccionDTO){
        Transaccion transaccion = new Transaccion();
        transaccion.setId(transaccionDTO.getId());
        transaccion.setPrecioTotal(transaccionDTO.getPrecioTotal());
        transaccion.setClient(userMgr.getOne(transaccionDTO.getClient()));
        return transaccion;
    }
    private Ticket ticketFromDto(TicketDTO ticketDTO) {
        TicketPk ticketPk = new TicketPk();


        ticketPk.setFuncion(funtionFromDto(ticketDTO.getFuncion_id()));
        ticketPk.setSeat(seatFromDto(ticketDTO.getSeat()));

        Ticket ticket = new Ticket();
        ticket.setLock(ticketDTO.isLock());
       if (ticketDTO.getTransaccionId() != 0) {
         ticket.setTransaccion(transaccionMgr.getOne(ticketDTO.getTransaccionId())); // IMPORTANTE QUE PASA CUNADO NO LOS COMPRARON ??? NO HAH YRANSACION EL GET ONE TE DEVUELCE NULL?? NDEAH
       }ticket.setPrice(ticketDTO.getPrice());
        ticket.setDiscount(ticketDTO.getDiscount());
        ticket.setBought(ticketDTO.isBought());
        ticket.setId(ticketPk);

        return ticket;
    }
    @PostMapping("/transaccion/{id}")
    public void save(@RequestBody List<TicketDTO> tickets,@PathVariable("id") String clientId)
    {
        long clientid= Long.parseLong(clientId);


        transaccionMgr.addTransaccion(tickets,clientid);
    }
    @GetMapping("transaccion/saldo/{id}")
        public String consultarSaldo(@PathVariable("id") long id){
        String retu = transaccionMgr.consultar(id)+"";
        return retu;
    }

    @DeleteMapping("/transaccion/{id}")
    public void delate(@PathVariable("id") long id){
        transaccionMgr.deleteTransaccion(id);
    }

    @GetMapping("/transaccion/{id}")
    public TransaccionDTO getOne(@PathVariable("id") long id){
        return transaccionMgr.getOne(id).toDTO();
    }
    @GetMapping("/transaccion/{name}")
    public List<TransaccionDTO> getByClientName(@PathVariable("name") String name ){
        List<TransaccionDTO> transaccionDTOS= new ArrayList<>();
        for(Transaccion transaccion:transaccionMgr.getByClintUsername(name)){
            transaccionDTOS.add(transaccion.toDTO());
        }
        return transaccionDTOS;

    }

    @GetMapping("/transaccion/{client_id}")
    public List<TransaccionDTO> getByClientID(@RequestBody long client_id ){
        List<TransaccionDTO> transaccionDTOS= new ArrayList<>();
        for(Transaccion transaccion:transaccionMgr.getByClintId(client_id)){
            transaccionDTOS.add(transaccion.toDTO());
        }
        return transaccionDTOS;
    }


}
