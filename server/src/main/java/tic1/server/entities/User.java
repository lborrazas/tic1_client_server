package tic1.server.entities;
import javax.persistence.*;


@Entity
@Table(name="User")
@DiscriminatorColumn(name="Type", discriminatorType =DiscriminatorType.STRING)
public class User {
    @Id
    @Column(name="id",length = 40)
    public String username;
    @Column(name = "pass",length = 40)
    public String password;
    @Column
    public int creditCard;

}
