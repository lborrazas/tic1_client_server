package tic1.server.persistence;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tic1.server.entities.*;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, TicketPk>{

    List<Ticket> findAllByIdFuncionIdDate(LocalDateTime today);
    List<Ticket> findAllByIdFuncionId(FunctionPK functionPK);
    List<Ticket> findAllByIdFuncionIdDateBetween(LocalDateTime todaydawn,LocalDateTime todaynigth);
    List<Ticket> findAllByIdFuncionIdSala(Sala sala);
    List<Ticket> findAllByIdFuncionIdSalaId(Long id);
    List<Ticket> findAllByTransaccion(Transaccion transaccion);
    List<Ticket> findAllByTransaccionClient(User client);
    List<Ticket> findAllByTransaccionClientId(long id);
    List<Ticket> findAllByTransaccionClientUsername(String username);
}
