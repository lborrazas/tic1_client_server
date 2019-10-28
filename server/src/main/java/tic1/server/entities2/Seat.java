package tic1.server.entities2;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Seat {
    private long row; //pK
    private long column; //pK

    @ManyToOne
    @JoinColumn(name = "id_sala")
    public Sala sala; //pK

}
