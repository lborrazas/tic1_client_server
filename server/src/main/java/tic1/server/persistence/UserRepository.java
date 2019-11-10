package tic1.server.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tic1.server.entities.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findAllByUsername(String name);
    //List<User> findAllByCreditCard(String  creditCards);
   // List<User> findAllByRoleContaining(String role);
    //List<User> findAllByProviderId(long  idProvider);
    //List<User> findAllByProviderName(String nameProvider);
   // List<User> findAllByType(String name);
}
