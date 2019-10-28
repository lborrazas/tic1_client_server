package tic1.server.entities2;

import javax.persistence.*;

@Entity
@Table
public class Seat {
    @Column
    private long row; //pK
    @Column
    private long column; //pK

    @ManyToOne
    @JoinColumn(name = "id_sala")
    private Sala sala; //pK


    public Seat() {
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
