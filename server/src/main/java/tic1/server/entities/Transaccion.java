package tic1.server.entities;

import javax.persistence.*;

@Entity
@Table
public class Transaccion {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client")
    private UserClient client;

}
