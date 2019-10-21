package tic1.server.entities;

import javax.persistence.*;

@Entity
@Table
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
}
