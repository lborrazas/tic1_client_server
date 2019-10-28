package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities2.Function;

@Repository
public interface FunctionRepository extends JpaRepository<Function,Long> {
}
