package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.SalaDTO;
import tic1.commons.transfers.SeatDTO;
import tic1.server.business.SalaMgr;
import tic1.server.business.SeatMgr;
import tic1.server.entities.Sala;
import tic1.server.entities.Seat;
import tic1.server.entities.SeatPk;

@RestController
public class SeatRestController {
    @Autowired
    SeatMgr seatMgr;
    @Autowired
    SalaMgr salaMgr;

    private Seat seatFromDto(SeatDTO seatDTO) {
        SeatPk seatPk = new SeatPk();
        seatPk.setColumna(seatDTO.getColumn());
        seatPk.setFila(seatDTO.getRow());
        seatPk.setSala(salaMgr.getSalaById(seatDTO.getSala_id()));

        Seat seat = new Seat();
        seat.setId(seatPk);

        return seat;
    }


    @PostMapping("/seat")
    public void save(@RequestBody SeatDTO seatDTO){

        Seat seat = seatFromDto(seatDTO);
        seatMgr.save(seat);
    }

    @DeleteMapping("/sala/{id}")
    public void delete(@PathVariable SeatDTO seatDTO){
    seatMgr.deleteSeat(seatFromDto(seatDTO).getId());
    }




}
