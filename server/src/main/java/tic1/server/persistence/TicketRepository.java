package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tic1.server.entities.Ticket;
import tic1.server.entities.TicketPk;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, TicketPk>{
}
