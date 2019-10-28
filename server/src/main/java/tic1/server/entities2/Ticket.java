package tic1.server.entities2;

import javax.persistence.*;

@Table
@Entity
public class Ticket {

    @EmbeddedId
    private TicketPk id;
    @Column
    private boolean isBought;
    @Column
    private float discount;
    @Column
    private float price;
    @OneToMany
    @JoinColumn(name = "id_client")
    private UserClient client;

    public Ticket() {
    }

    public TicketPk getId() {
        return id;
    }

    public void setId(TicketPk id) {
        this.id = id;
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
