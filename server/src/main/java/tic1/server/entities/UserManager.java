package tic1.server.entities;

import tic1.commons.transfers.UserDTO;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Manager")
public class UserManager extends User {
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

    @Override
    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(super.getPassword());
        userDTO.setUsername(super.getUsername());
        userDTO.setId(super.getId());
        userDTO.setType("Manager");
        userDTO.setRole(this.role);
        userDTO.setProvider(this.provider.getId());
        return  userDTO;
    }
}
