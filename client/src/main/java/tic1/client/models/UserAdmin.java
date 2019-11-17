package tic1.client.models;

import tic1.commons.transfers.UserDTO;


public class UserAdmin extends User{

    public UserAdmin(UserDTO userDTO){
        super(userDTO);
    }
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


