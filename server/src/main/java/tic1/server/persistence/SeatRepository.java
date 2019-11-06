package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Sala;
import tic1.server.entities.Seat;
import tic1.server.entities.SeatPk;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatPk> {

    List<Seat> findAllByIdSala(Sala sala);

}
