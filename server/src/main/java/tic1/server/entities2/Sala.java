package tic1.server.entities2;


import javax.persistence.*;

@Entity
@Table(name= "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    public Cinema cinema;

}
