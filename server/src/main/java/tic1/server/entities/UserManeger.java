package tic1.server.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Manager")
public class UserManeger extends User {
    @Column
    private String role;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Provider provider;  //todo provider hasMany Managers

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
