package tic1.server.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Ticket {

    @EmbeddedId
    private TicketPk id;
    @Column
    private boolean isBought;
    @Column
    private float discount;
    @Column
    private float price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private UserClient client;

    @Column
    private String sala;
    @Column
    private long salaid;
    @Column
    private LocalDateTime fecha;

    public Ticket() {
    }

    public TicketPk getId() {
        return id;
    }

    public void setId(TicketPk id) {
        this.id = id;
        this.salaid=id.getFuncion().getId().getSala().getId();
        this.sala=id.getFuncion().getId().getSala().getName();
        this.fecha=id.getFuncion().getId().getDate();
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public UserClient getClient() {
        return client;
    }

    public void setClient(UserClient client) {
        this.client = client;
    }
}
