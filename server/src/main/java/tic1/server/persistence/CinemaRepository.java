package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tic1.server.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema,Long> {
}
