package tic1.server.entities;

import tic1.commons.transfers.UserDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Admin")
public class UserAdmin extends User{
    @Override
    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(super.getPassword());
        userDTO.setUsername(super.getUsername());
        userDTO.setId(super.getId());
        userDTO.setType("Admin");
        return  userDTO;
    }


}


