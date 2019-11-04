package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Genre;
@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
}
