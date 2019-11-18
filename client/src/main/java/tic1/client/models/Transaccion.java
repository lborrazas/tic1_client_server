package tic1.client.models;

import tic1.commons.transfers.TransaccionDTO;


public class Transaccion {

    private long id;

    private long client;

    //  @Column
    // private List<String> consumibles;

    private int precioTotal;

    public Transaccion(TransaccionDTO transaccionDTO) {
        this.client = transaccionDTO.getClient();
        this.id = transaccionDTO.getId();
        this.precioTotal = transaccionDTO.getPrecioTotal();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClient() {
        return client;
    }

    public void setClient(long client) {
        this.client = client;
    }

    // public List<String> getConsumibles() {
    //   return consumibles;
    //}

    //public void setConsumibles(List<String> consumibles) {
    //  this.consumibles = consumibles;
    //}

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public TransaccionDTO toDTO() {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setClient(this.getClient());
        transaccionDTO.setId(this.id);
        transaccionDTO.setPrecioTotal(this.precioTotal);
        return transaccionDTO;
    }
}

