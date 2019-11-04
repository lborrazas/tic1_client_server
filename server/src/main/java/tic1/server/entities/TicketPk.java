package tic1.server.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TicketPk implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_function",referencedColumnName = "second_id")
    private Function function ;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn,@JoinColumn,@JoinColumn})
    private Seat seat;

    public TicketPk() {
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
