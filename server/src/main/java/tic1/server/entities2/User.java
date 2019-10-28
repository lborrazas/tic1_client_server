package tic1.server.entities2;
import javax.persistence.*;


@Entity
@Table(name="User")
@DiscriminatorColumn(name="Type", discriminatorType =DiscriminatorType.STRING)
public class User {
    @Id
    @Column(name="id",length = 40)
    public String username;
    @Column(name = "password",length = 40)
    public String password;

}
