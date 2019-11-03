package tic1.server.entities;

import javax.persistence.*;

@Embeddable
public class TicketPk {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn(),@JoinColumn()})
    private Function function ;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
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
