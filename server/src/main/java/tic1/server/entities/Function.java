package tic1.server.entities;

import tic1.commons.transfers.FunctionDTO;

import javax.persistence.*;

public class Function {
    @EmbeddedId
    @Id
    private FunctionPK functionPK;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movieId")
    private Movie movie;

    private FunctionDTO toDTO(){
        FunctionDTO tempDTO = new FunctionDTO();
        tempDTO.setMovie(this.movie.toDTO());
        tempDTO.setStartTime(this.functionPK.getDate());
        tempDTO.setCinemaName(this.functionPK.getSala().getCinema().getName());
        tempDTO.setLocal(this.functionPK.getSala().getCinema().getLocation());
        tempDTO.setProviderName(this.functionPK.getSala().getCinema().getProvider().getName());
        tempDTO.setSala(this.functionPK.getSala().getId());
        return tempDTO;

    }

    public Function() {
    }

    public FunctionPK getFunctionPK() {
        return functionPK;
    }

    public void setFunctionPK(FunctionPK functionPK) {
        this.functionPK = functionPK;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
