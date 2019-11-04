package tic1.server.entities;

import javax.persistence.*;

@Entity
@Table
public class Seat {
@EmbeddedId
private SeatPk id;


    public Seat() {
    }

}
