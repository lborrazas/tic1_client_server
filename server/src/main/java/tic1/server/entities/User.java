package tic1.server.entities;


import tic1.commons.transfers.UserDTO;

import javax.persistence.*;


@Entity
@Table(name = "Users")
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 40, nullable = false)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(this.password);
        userDTO.setUsername(this.username);
        userDTO.setId(this.id);
        return userDTO;
    }
}
