package tic1.server.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class FunctionPK implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salaId",referencedColumnName = "id")
    private Sala sala;
    @Column
    private LocalDateTime date;

    public FunctionPK() {
    }

    public FunctionPK(Sala sala, LocalDateTime date) {
        this.sala = sala;
        this.date = date;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
