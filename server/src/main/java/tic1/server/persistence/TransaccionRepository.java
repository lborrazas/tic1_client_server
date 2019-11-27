package tic1.server.persistence;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import tic1.server.entities.Transaccion;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion,Long> {
    public  List<Transaccion> findAllByClient(Client client);
    public List<Transaccion> findAllByClientId(long id);
    public List<Transaccion> findAllByClientUsername(String username);
    public List<Transaccion> findTopByOrderByIdDesc();
}

