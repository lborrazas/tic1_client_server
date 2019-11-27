package tic1.client.models;

import tic1.commons.transfers.UserDTO;


public class UserManager extends User {

    public UserManager(UserDTO userDTO) {
        super(userDTO);
        this.provider = userDTO.getProvider();
        this.role = userDTO.getRole();
    }

    private String role;

    private long provider;  //todo provider hasMany Managers

    public UserManager() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getProvider() {
        return provider;
    }

    public void setProvider(long provider) {
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
        userDTO.setProvider(this.provider);
        return userDTO;
    }
}
