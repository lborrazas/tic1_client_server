package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tic1.server.entities2.Seat;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
