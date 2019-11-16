package tic1.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tic1.commons.transfers.UserDTO;
import tic1.server.business.ProviderMgr;
import tic1.server.business.UserMgr;
import tic1.server.entities.User;
import tic1.server.entities.UserAdmin;
import tic1.server.entities.UserClient;
import tic1.server.entities.UserManeger;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    UserMgr userMgr;
    @Autowired
    ProviderMgr providerMgr;

    public User userFromDto(UserDTO userDTO){

        User user;
        switch (userDTO.getType()){
            case"Manager":
                UserManeger manager = new  UserManeger();
                 manager.setProvider(providerMgr.getById(userDTO.getProvider()));
                 manager.setRole(userDTO.getRole());
                 user = manager;
                 break;
            case"Client":
                UserClient userClient= new UserClient();
                userClient.setCreditCard(userDTO.getCreditCard());
                user=userClient;
                break;
            case"Admin":
                UserAdmin userAdmin= new UserAdmin();
                user= userAdmin;
                break;
            default:
                user= new User();
        }

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return user;
    }
    @PostMapping("/user")
    public void save(@RequestBody UserDTO userDTO)
    { userMgr.save(userFromDto(userDTO));

    }

    @DeleteMapping("/user/{id}")
    public void delate(@RequestBody long id){
        userMgr.delete(id);
    }

    @GetMapping("/user/{id}")
    public UserDTO getOne(@RequestBody long id){
        return userMgr.getOne(id).toDTO();
    }
    @GetMapping("/user/{name}")
    public List<UserDTO> getBy(@RequestBody String name){
        List<UserDTO> userDTOS= new ArrayList<>();
        for(User user:userMgr.getByname(name)){
            userDTOS.add(user.toDTO());
        }
        return userDTOS;

    }

}
