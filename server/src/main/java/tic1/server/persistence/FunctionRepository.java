package tic1.server.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Funcion;
import tic1.server.entities.FunctionPK;
import tic1.server.entities.Movie;
import tic1.server.entities.Sala;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDate.now;

@Repository
public interface FunctionRepository extends JpaRepository<Funcion, FunctionPK> {

  List<Funcion> findAllByIdSala(Sala sala);
  List<Funcion> findAllByIdSalaId(long salaid);

//  @Query(value = "SELECT * FROM FUNCION WHERE LASTNAME = ?1",
//          countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
//          nativeQuery = true)
//  Page<Funcion> findByProvideer(String lastname, Pageable pageable);

  @Query(value = "SELECT * FROM FUNCION WHERE SALA_ID1 IN (SELECT ID FROM SALA WHERE ID_CINEMA IN (" +
          "SELECT ID FROM CINEMA WHERE ID_PROVIDER = ?1))",
          nativeQuery = true)
  List<Funcion> findByProvider(long providerId);

  @Query(value = "SELECT * FROM FUNCION WHERE SALA_ID1 IN (SELECT ID FROM SALA WHERE ID_CINEMA IN (" +
          "SELECT ID FROM CINEMA WHERE ID_PROVIDER = ?1))",
          nativeQuery = true)
  Page<Funcion> findByProviderPaged(long providerId, Pageable pageable);

  List<Funcion> findAllByMovieAndIdDateAfter(Movie movie, LocalDateTime today);//cuando se llame la funcion usar now()
  List<Funcion> findAllByMovieAndIdDate(Movie movie, LocalDateTime today);//cuando se llame la funcion usar now()
  List<Funcion> findAllByIdDateAfter(LocalDateTime today);//cuando se llame la funcion usar now()

  //  List<Funcion> findAllByMovie(Movie movie, Pageable pageable);
}
