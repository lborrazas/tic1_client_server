package tic1.commons.transfers;

public class TransaccionDTO {
    private int precioTotal;
    private long id;
    private long client_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClient() {
        return client_id;
    }

    public void setClient(long client) {
        this.client_id = client;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }
}
