package tic1.client.models;


import tic1.commons.transfers.SeatDTO;

import java.io.Serializable;

public class Seat implements Serializable {

    private long fila;

    private long columna;

    private long sala;


    public Seat(SeatDTO seat) {
        this.columna= seat.getColumn();
        this.fila = seat.getRow();
        this.sala= seat.getSala_id();
    }
 public Seat() {
    }

    public long getSala() {
        return sala;
    }

    public void setSala(long sala) {
        this.sala = sala;
    }

    public long getFila() {
        return fila;
    }

    public void setFila(long fila) {
        this.fila = fila;
    }

    public long getColumna() {
        return columna;
    }

    public void setColumna(long columna) {
        this.columna = columna;
    }

    public SeatDTO toDTO(){
        SeatDTO seatDTO= new SeatDTO();
        seatDTO.setSala_id(this.sala);
        seatDTO.setRow(this.fila);
        seatDTO.setColumn(this.columna);
        return seatDTO;
    }


}
