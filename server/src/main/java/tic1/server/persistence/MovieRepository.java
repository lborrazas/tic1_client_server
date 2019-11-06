package tic1.server.persistence;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Actor;
import tic1.server.entities.Genre;
import tic1.server.entities.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);
    List<Movie> findAllByName(String name, Pageable pageable);
    List<Movie> findAllByGenres(List<Genre> genres, Pageable pageable);
    List<Movie> findAllByGenres(Genre genres, Pageable pageable);
    List<Movie> findAllByActors(Actor actor, Pageable pageable);



}
