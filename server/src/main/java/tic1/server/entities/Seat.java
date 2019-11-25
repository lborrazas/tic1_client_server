package tic1.server.entities;

import tic1.commons.transfers.SeatDTO;

import javax.persistence.*;

@Entity
@Table
public class Seat {
@EmbeddedId
private SeatPk id;
@Column
private boolean isLocked;


    public Seat() {
    }

    public SeatPk getId() {
        return id;
    }

    public void setId(SeatPk id) {
        this.id = id;
    }
    public Seat(SeatDTO dto) {
        id.setColumna(dto.getColumn());
        id.setFila(dto.getRow());
        this.isLocked = dto.isLocked();
    }

    public SeatDTO toDTO() {
    SeatDTO seatDTO= new SeatDTO();
    seatDTO.setColumn(this.id.getColumna());
    seatDTO.setRow(this.id.getFila());
    seatDTO.setSala_id(this.id.getSala().getId());
    seatDTO.setLocked(this.isLocked);
        return seatDTO;
    }
}
