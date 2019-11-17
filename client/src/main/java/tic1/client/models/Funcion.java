package tic1.client.models;

import tic1.commons.transfers.FunctionDTO;

import java.time.LocalDateTime;

public class Funcion {


    private Movie movie;

    private long secondId;

    private long salaId;

    private LocalDateTime date;

    private long cinemaId;

    public long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(long cinemaId) {
        this.cinemaId = cinemaId;
    }



    public Funcion(FunctionDTO dto) {
        this.setDate(dto.getStartTime());
        this.setMovie(new Movie(dto.getMovie()));
        this.setSecondId(dto.getSala());
        this.setSecondId(dto.getSecondId());
        this.setCinemaId(dto.getCinemaId());
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

    public long getSalaId() {
        return salaId;
    }

    public void setSalaId(long salaId) {
        this.salaId = salaId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public FunctionDTO toDTO() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setSala(this.salaId);
     //   functionDTO.setCinemaName(this.cinemaName);
      //  functionDTO.setLocal(this.);
       // functionDTO.setProviderName(this.getId().getSala().getCinema().getProvider().getName());
       // functionDTO.setStartTime(this.getId().getDate());
        functionDTO.setMovie(this.getMovie().toDTO());
        functionDTO.setCinemaId(this.cinemaId);

        return functionDTO;
    }
}
