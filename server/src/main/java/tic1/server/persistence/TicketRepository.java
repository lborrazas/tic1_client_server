package tic1.server.persistence;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tic1.server.entities.Ticket;
import tic1.server.entities.TicketPk;
import tic1.server.entities.Transaccion;
import tic1.server.entities.UserClient;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, TicketPk>{
    List<Ticket> findAllByIdFuncionIdDate(LocalDateTime today);
    List<Ticket> findAllByIdFuncionIdSala(String sala);
    List<Ticket> findAllByTransaccion(Transaccion transaccion);
    List<Transaccion> findAllByTransaccionClient(Client client);
}
