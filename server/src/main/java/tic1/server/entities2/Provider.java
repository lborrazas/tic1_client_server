package tic1.server.entities2;

import javax.persistence.*;

@Entity
@Table
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
}
