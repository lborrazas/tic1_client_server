package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Sala;
import tic1.server.entities.Seat;
import tic1.server.entities.SeatPk;
import tic1.server.persistence.SeatRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class SeatMgr {
    @Autowired
    SeatRepository seatRepository;

    public List<Seat> findAll(){ return seatRepository.findAll(); }

    public  void save(Seat seat){
        seatRepository.save(seat);

    }
    public Seat getOne(@PathVariable("id") SeatPk id){
        return seatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }
    public void updateSeat(@PathVariable("id") SeatPk id, @Valid @RequestBody Seat seat){
        Seat existingSeat = seatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        existingSeat.setId(seat.getId());
        seatRepository.save(existingSeat);
    }

    public ResponseEntity<?> deleteSeat(@PathVariable("id") SeatPk id) {

        Seat ticket = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        seatRepository.delete(ticket);

        return ResponseEntity.ok().build();
    }

    List<Seat> getBySala(Sala sala){
        return seatRepository.findAllByIdSala(sala);
    }
    List<Seat> getBySalaId(Long salaid){
    return seatRepository.findAllByIdSalaId(salaid);
    }


}
