package tic1.server.entities;

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
}
