package tic1.server.persistence;

import org.springframework.data.domain.Pageable;
import tic1.server.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String title);
    List<Movie> findAllByName(String name, Pageable pageable);
    List<Movie> findAllByGenre(String genre, Pageable pageable);

}
