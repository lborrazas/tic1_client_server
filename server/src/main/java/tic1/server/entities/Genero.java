package tic1.server.entities;

import javax.persistence.*;

@Entity
@Table
public class Genero {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
@Column(name = "genero")
    private String genero;
}
