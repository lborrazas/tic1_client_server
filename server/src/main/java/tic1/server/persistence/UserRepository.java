package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tic1.server.entities.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findAllByUsername(String name);
<<<<<<< HEAD

    ////public final String Type  = "SELECT * FROM  User  WHERE  type = :product";

 //   @Query(Type)
  //  List<User> findByPreference(@Param("product") String prouduct);
=======
/*
    public final String Type  = "SELECT * FROM  User  WHERE  type = :product";

    @Query(Type)
    List<User> findByPreference(@Param("product") String prouduct);*/
>>>>>>> 2c974d09be1dd37498235e3737f2dc272cb7979d

    //List<User> findAllByCreditCard(String  creditCards);
   // List<User> findAllByRoleContaining(String role);
    //List<User> findAllByProviderId(long  idProvider);
    //List<User> findAllByProviderName(String nameProvider);
   // List<User> findAllByType(String name);
}
