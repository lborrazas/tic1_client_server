package tic1.server.entities;

import javax.persistence.*;

@Entity
@Table
public class Seat {
@EmbeddedId
private SeatPk id;
@Column
private long salaid;
@Column
private String sala;


    public Seat() {

    }

    public SeatPk getId() {
        return id;
    }

    public void setId(SeatPk id) {
        this.id = id;
        this.salaid=id.getSala().getId();
        this.sala=id.getSala().getName();
    }
}
