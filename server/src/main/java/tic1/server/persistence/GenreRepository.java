package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import tic1.server.entities2.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
