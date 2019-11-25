package tic1.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tic1.commons.business.exceptions.ResourceNotFoundException;
import tic1.server.entities.Cinema;
import tic1.server.entities.Sala;
import tic1.server.entities.Seat;
import tic1.server.entities.SeatPk;
import tic1.server.persistence.SalaRepository;
import tic1.server.persistence.SeatRepository;

import java.util.List;

@Service
public class SalaMgr {

    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private SeatRepository seatRepository;
    public Sala getSalaById(long id) {
        return salaRepository.findById(id).get();
    }

    public void addSala(Sala sala) {


        salaRepository.save(sala);

        for (int n=1;n <= sala.getMaxfila(); n++) {
            for (int m=1; m <= sala.getMaxcolum(); m++) {
                SeatPk seatPk = new SeatPk();
                seatPk.setColumna(m);
                seatPk.setFila(n);
                seatPk.setSala(sala);
                Seat seat = new Seat();
                seat.setId(seatPk);
                seatRepository.save(seat);
            }
        }
    }


    public void deleteSala(Sala sala) {
        salaRepository.delete(sala);
    }

    public void updateSala(Long id, Sala sala) {
        Sala existingSala = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala", "id", id));
        existingSala.setCinema(sala.getCinema());
        salaRepository.save(existingSala);

    }

    public List<Sala> getByName(String name) {
        return salaRepository.findAllByName(name);
    }

    ;

    public List<Sala> findAllByCinema(Cinema cinema) {
        return salaRepository.findAllByCinema(cinema);
    }
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }


    public ResponseEntity<?> delateById(@PathVariable("id") Long id) {

        Sala user = salaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        salaRepository.delete(user);

        return ResponseEntity.ok().build();
    }


}

