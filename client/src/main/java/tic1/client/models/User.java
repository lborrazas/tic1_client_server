package tic1.client.models;


import tic1.commons.transfers.UserDTO;


public class User {
    private  long id;
    private String username;
    private String password;

    public User(UserDTO userDTO) {
        this.id=userDTO.getId();
        this.password =userDTO.getPassword();
        this.username = userDTO.getUsername();

    }

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

    public  UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(this.password);
        userDTO.setUsername(this.username);
        userDTO.setId(this.id);
        return  userDTO;
    }
}
