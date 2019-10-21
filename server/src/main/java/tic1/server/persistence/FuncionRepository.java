package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Funcion;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion,Long> {
}
