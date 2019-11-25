package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Cinema;
import tic1.server.entities.Sala;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala,Long> {

    List<Sala> findAllByName(String name);
    List<Sala> findAll();
    List<Sala> findAllByCinema(Cinema cinema);

    List<Sala> findTopByOrderByIdDesc();
}
