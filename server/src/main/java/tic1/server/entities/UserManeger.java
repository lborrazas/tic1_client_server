package tic1.server.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@DiscriminatorValue("Manager")
public class UserManeger extends User {
    private String role;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    public Provider provider;  //todo provider hasMany Managers

}
