package tic1.commons.transfers;

public class TicketDTO {

    private FunctionDTO funcion_id;
    private SeatDTO seat;
    private boolean isLock;
    private boolean isBought;
    private float discount;
    private float price;
    private long transaccion_id;

    public TicketDTO() {
    }

    public FunctionDTO getFuncion_id() {
        return funcion_id;
    }

    public void setFuncion_id(FunctionDTO funcion_id) {
        this.funcion_id = funcion_id;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
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

    public long getTransaccionId() {
        return transaccion_id;
    }

    public void setTransaccionId(long transaccion_id) {
        this.transaccion_id =transaccion_id;
    }
}
