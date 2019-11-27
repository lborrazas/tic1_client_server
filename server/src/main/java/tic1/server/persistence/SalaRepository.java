package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Cinema;
import tic1.server.entities.Sala;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala,Long> {

    public List<Sala> findAllByName(String name);
    public List<Sala> findAll();
    public List<Sala> findAllByCinema(Cinema cinema);

    public List<Sala> findTopByOrderByIdDesc();

    public List<Sala> findAllByCinemaId(long id);
}
