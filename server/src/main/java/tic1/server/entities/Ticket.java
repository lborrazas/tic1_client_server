package tic1.server.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @EmbeddedId
    private TicketId id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({@JoinColumn,@JoinColumn})
    private Funcion espectaculo;
    @Column(insertable = false,updatable = false)
    private int n;
    @Column(insertable = false,updatable = false)
    private int m;



}
