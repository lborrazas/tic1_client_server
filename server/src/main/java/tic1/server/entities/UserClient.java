package tic1.server.entities;

import tic1.commons.transfers.UserDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Client")
public class UserClient  extends User{



    @Column
    private String creditCard;


    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(super.getPassword());
        userDTO.setUsername(super.getUsername());
        userDTO.setId(super.getId());
        userDTO.setType("Client");
        userDTO.setCreditCard(this.creditCard);
        return  userDTO;
    }
}
