package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities2.Actor;

@Repository
public interface ActorsRepository extends JpaRepository<Actor,Long> {
}
