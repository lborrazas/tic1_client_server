package tic1.server.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class SeatPk implements Serializable {

    @Column
    private long row; //pK
    @Column
    private long column; //pK


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sala")
    private Sala sala; //pK

    public SeatPk() {
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public long getRow() {
        return row;
    }

    public void setRow(long row) {
        this.row = row;
    }

    public long getColumn() {
        return column;
    }

    public void setColumn(long column) {
        this.column = column;
    }


}
