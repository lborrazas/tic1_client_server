package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Funcion;
import tic1.server.entities.FunctionPK;
import tic1.server.entities.Movie;
import tic1.server.entities.Sala;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDate.now;

@Repository
public interface FunctionRepository extends JpaRepository<Funcion, FunctionPK> {

  List<Funcion> findAllByIdSala(Sala sala);
  List<Funcion> findAllByIdSalaId(long salaid);

  List<Funcion> findAllByMovieAndIdDateAfter(Movie movie, LocalDateTime today);//cuando se llame la funcion usar now()
  List<Funcion> findAllByMovieAndIdDate(Movie movie, LocalDateTime today);//cuando se llame la funcion usar now()
  List<Funcion> findAllByIdDateAfter(LocalDateTime today);//cuando se llame la funcion usar now()
  //  List<Funcion> findAllByMovie(Movie movie, Pageable pageable);
}
