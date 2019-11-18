package tic1.client.models;

import tic1.commons.transfers.TicketDTO;


public class Ticket {

    private Funcion funcion;

    private Seat seat;

    private boolean isBought;

    private boolean isLock;

    private float discount;

    private float price;

    private long transaccionId;

    public Ticket(TicketDTO dto) {
        this.isBought = dto.isBought();
        this.discount = dto.getDiscount();
        this.isLock = dto.isLock();
        this.price = dto.getPrice();
        this.funcion = new Funcion(dto.getFuncion_id());
        this.transaccionId = dto.getTransaccionId();

    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
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

    public long getTransaccion() {
        return transaccionId;
    }

    public void setTransaccion(long transaccion) {
        this.transaccionId = transaccion;
    }

    public TicketDTO toDTO() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setBought(this.isBought());
        ticketDTO.setDiscount(this.getDiscount());
        ticketDTO.setFuncion_id(this.funcion.toDTO());
        ticketDTO.setLock(this.isLock());
        ticketDTO.setPrice(this.getPrice());
        ticketDTO.setTransaccionId(this.getTransaccion());
        return ticketDTO;
    }
}
