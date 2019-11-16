package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.SeatDTO;
import tic1.server.business.SalaMgr;
import tic1.server.business.SeatMgr;
import tic1.server.entities.Seat;
import tic1.server.entities.SeatPk;

import java.util.ArrayList;
import java.util.List;

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

    @DeleteMapping("/seat/{seatDTO}")
    public void delete(@PathVariable("seatDTO") SeatDTO seatDTO){
    seatMgr.deleteSeat(seatFromDto(seatDTO).getId());
    }

    @GetMapping("/seat/{seatDTO}")
    public SeatDTO getOne(@PathVariable("seatDTO")  SeatDTO seatDTO){
        Seat a = seatFromDto(seatDTO);
         return seatMgr.getOne(a.getId()).toDTO();
    }
    @GetMapping("/seat/{sala_id}")
    public List<SeatDTO> getBySalaId(@PathVariable("sala_id") long sala_id){
        List<SeatDTO> seatDTOS = new ArrayList<>();
        for (Seat seat:seatMgr.getBySalaId(sala_id)){
            seatDTOS.add(seat.toDTO());
        }
        return seatDTOS;
    }



}
