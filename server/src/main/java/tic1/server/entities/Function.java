package tic1.server.entities;

import javax.persistence.*;

public class Function {
    @EmbeddedId
    @Id
    private FunctionPK id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movieId")
    private Movie movie;

    public Function() {
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
}
