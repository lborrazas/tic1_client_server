package tic1.server.entities;

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

    @Column
    private LocalDateTime fecha;

    @Column
    private String sala;
    @Column
    private long salaid;

    public Funcion() {

    }

    public FunctionPK getId() {
        return id;
    }

    public void setId(FunctionPK id) {
        this.id = id;
        this.fecha=id.getDate();
        this.sala=id.getSala().getName();
        this.salaid=id.getSala().getId();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
