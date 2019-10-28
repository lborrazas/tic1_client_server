package tic1.server.entities2;

import tic1.server.entities.Ticket;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@DiscriminatorValue("Client")
public class UserClient {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    private String creditCard;


    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
