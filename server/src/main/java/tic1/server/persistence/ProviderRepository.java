package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.Provider;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Long> {
    List<Provider>  findAllByName(String name);
}
