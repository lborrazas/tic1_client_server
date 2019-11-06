package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Genre;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    List<Genre> findAllByGenre(String genre);

    List<Genre> findByGenre(String genre); //todo cambiar por la de juan
}
