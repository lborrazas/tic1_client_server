package tic1.client.models;

import tic1.commons.transfers.FunctionDTO;

import java.time.LocalDateTime;

public class Funcion {


    private Movie movie;

   private long secondId;

   private String salaId;

    private LocalDateTime date;

    public Funcion() {
    }

    public Funcion(FunctionDTO dto) {
        this.setDate(dto.getStartTime());
        this.setMovie(new Movie(dto.getMovie()));
        this.setSecondId(dto.getSala());
        this.setSecondId(dto.getSecondId());
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public long getSecondId() {
        return secondId;
    }

    public void setSecondId(long secondId) {
        this.secondId = secondId;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
