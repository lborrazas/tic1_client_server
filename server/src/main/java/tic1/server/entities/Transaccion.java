package tic1.server.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Transaccion {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client",foreignKey = @ForeignKey(name = "fkcliente_transaccion"))
    private User client;

    //  @Column
   // private List<String> consumibles;

    @Column
    private  int precioTotal;

    public Transaccion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
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

}

