package tic1.server.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class FunctionPK {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salaId",referencedColumnName = "id")
    private Sala sala;
    @Column
    private LocalDateTime date;

    public FunctionPK() {
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