package tic1.client.models;

import tic1.commons.transfers.UserDTO;




public class UserClient extends User{

    private String creditCard;

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

   public UserClient(UserDTO userDTO){
        super(userDTO);
        this.creditCard=userDTO.getCreditCard();
    }

    public UserClient() {
        super();
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
