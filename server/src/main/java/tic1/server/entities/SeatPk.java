package tic1.server.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class SeatPk implements Serializable {

    @Column
    private long fila; //pK
    @Column
    private long columna; //pK

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sala",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fksala_seat"))
    private Sala sala; //pK


    public SeatPk() {
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public long getFila() {
        return fila;
    }

    public void setFila(long fila) {
        this.fila = fila;
    }

    public long getColumna() {
        return columna;
    }

    public void setColumna(long columna) {
        this.columna = columna;
    }



}
