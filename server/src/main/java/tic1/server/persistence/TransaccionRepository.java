package tic1.server.persistence;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import tic1.server.entities.Transaccion;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion,Long> {

    List<Transaccion> findAllByClient(Client client);
}
