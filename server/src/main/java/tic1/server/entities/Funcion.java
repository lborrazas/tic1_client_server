package tic1.server.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tic1.commons.transfers.FunctionDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Funcion {
    @EmbeddedId
    @Id
    private FunctionPK id;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "movieId",foreignKey = @ForeignKey(name = "fk_sala_function"))
    private Movie movie;


    public Funcion() {

    }

    public FunctionPK getId() {
        return id;
    }

    public void setId(FunctionPK id) {
        this.id = id;

    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public FunctionDTO toDTO() {
        FunctionDTO functionDTO = new FunctionDTO();
        functionDTO.setSala(this.getId().getSala().getId());
       /// functionDTO.setCinemaName(this.getId().getSala().getCinema().getName());
        //functionDTO.setLocal(this.getId().getSala().getCinema().getLocation());
       // functionDTO.setProviderName(this.getId().getSala().getCinema().getProvider().getName());
        functionDTO.setCinemaId(this.getId().getSala().getCinema().getId());
        functionDTO.setStartTime(this.getId().getDate());
        functionDTO.setMovie(this.getMovie().toDTO());
        return functionDTO;
    }
}
