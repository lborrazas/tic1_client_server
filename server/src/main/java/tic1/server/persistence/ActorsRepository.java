package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Actor;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ActorsRepository extends JpaRepository<Actor,Long> {

    //List<Actor> findAllByName(String name, Pageable pageable);
    // List<Actor> findByName()
}
