package tic1.server.entities;

import tic1.commons.transfers.SeatDTO;

import javax.persistence.*;

@Entity
@Table
public class Seat {
@EmbeddedId
private SeatPk id;


    public Seat() {

    }

    public SeatPk getId() {
        return id;
    }

    public void setId(SeatPk id) {
        this.id = id;

    }

    public SeatDTO toDTO() {
    SeatDTO seatDTO= new SeatDTO();
    seatDTO.setColumn(this.id.getColumna());
    seatDTO.setRow(this.id.getFila());
    seatDTO.setSala_id(this.id.getSala().getId());
        return seatDTO;
    }
}
