package tic1.server.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TicketPk implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_function",referencedColumnName = "second_id")
    private Funcion funcion;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(foreignKey = @ForeignKey(name = "FkRow_ticket")),@JoinColumn(foreignKey = @ForeignKey(name = "fkcolum_ticket")),@JoinColumn(foreignKey = @ForeignKey(name = "fksala_ticket"))})
    private Seat seat;

    public TicketPk() {
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
}
