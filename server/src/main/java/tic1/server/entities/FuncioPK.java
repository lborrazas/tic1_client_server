package tic1.server.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class FuncioPK implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id",referencedColumnName = "id")
    private Sala salon;


    public FuncioPK() {
    }

    public FuncioPK(Sala salon, LocalDate fecha) {
        this.salon = salon;
        this.fecha = fecha;
    }

    @Column
    private LocalDate fecha;

    public Sala getSalon() {
        return salon;
    }

    public void setSalon(Sala salon) {
        this.salon = salon;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
