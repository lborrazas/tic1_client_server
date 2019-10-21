package tic1.server.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
    public class TicketId  implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(),@JoinColumn()})
    private Funcion funcion ;
    @Column
    private int m;
    @Column
    private int n;

    public TicketId() {
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
