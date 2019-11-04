package tic1.server.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Client")
public class UserClient  extends User{



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private List<Ticket> tickets;

    private String creditCard;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
