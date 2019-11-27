package tic1.server.entities;

import org.hibernate.annotations.OnDelete;
import tic1.commons.transfers.TicketDTO;

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
    private boolean isLock;
    @Column
    private float discount;
    @Column
    private float price;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "transaccion",foreignKey = @ForeignKey(name = "fktransaccion_ticket"))
    private Transaccion transaccion;


    public Ticket() {
    }



    public TicketPk getId() {
        return id;
    }

    public void setId(TicketPk id) {
        this.id = id;

    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public boolean getIsBought() {
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

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public TicketDTO toDTO() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setBought(this.getIsBought());
        ticketDTO.setDiscount(this.getDiscount());
        ticketDTO.setFuncion_id(this.id.getFuncion().toDTO());
        ticketDTO.setLock(this.isLock());
        ticketDTO.setPrice(this.getPrice());
        ticketDTO.setSeat(this.id.getSeat().toDTO());
        if (this.getTransaccion() != null)ticketDTO.setTransaccionId(this.getTransaccion().getId());
    return ticketDTO;
    }
}
