package tic1.server.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Admin")
public class UserAdmin extends User{


}


