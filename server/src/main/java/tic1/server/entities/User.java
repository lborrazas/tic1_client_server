package tic1.server.entities;


import javax.persistence.*;


@Entity
@Table(name="User")
@DiscriminatorColumn(name="Type", discriminatorType =DiscriminatorType.STRING)
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)

    private String username;
    @Column(name = "password",length = 40)
    private String password;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
