package tic1.client.models;


import tic1.commons.transfers.SeatDTO;

import java.io.Serializable;

public class Seat implements Serializable {

    private long fila;

    private long columna;

    private Sala sala;


    public Seat() {
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
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
        seatDTO.setSala_id(this.sala.getId());
        seatDTO.setRow(this.fila);
        seatDTO.setColumn(this.columna);
        return seatDTO;
    }


}
