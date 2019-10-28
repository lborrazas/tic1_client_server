package tic1.server.entities2;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class FunctionPK {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salaId",referencedColumnName = "id")
    private Sala salaId;
    @Column
    private LocalDateTime date;

    public FunctionPK() {
    }

    public Sala getSalaId() {
        return salaId;
    }

    public void setSalaId(Sala salaId) {
        this.salaId = salaId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
