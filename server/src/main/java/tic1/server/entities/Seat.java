package tic1.server.entities;

import tic1.commons.transfers.SeatDTO;
import tic1.server.business.SalaMgr;

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
        SeatPk seatPk = new SeatPk();
        SalaMgr sMgr = new SalaMgr();
        seatPk.setColumna(dto.getColumn());
        seatPk.setFila(dto.getRow());
        seatPk.setSala(sMgr.getSalaById(dto.getSala_id()));

        this.id = seatPk;
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
