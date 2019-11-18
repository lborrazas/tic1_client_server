package tic1.server.entities;

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
    @JoinColumn(name = "movieId",foreignKey = @ForeignKey(name = "fksala_function"))
    private Movie movie;

    @Column(name = "second_id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long secondId;

    public long getSecondId() {
        return secondId;
    }

    public void setSecondId(long secondId) {
        this.secondId = secondId;
    }

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
