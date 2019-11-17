package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.User;
import tic1.client.models.UserAdmin;
import tic1.client.models.UserClient;
import tic1.client.models.UserManeger;
import tic1.commons.transfers.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRestTemplate {
    public User showActor(long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO> response = restTemplate.exchange(
                "http://localhost:8080/user/" + id, HttpMethod.GET, null, UserDTO.class);
        UserDTO userDTO = response.getBody();
        switch (userDTO.getType()){
            case "Admin":
                return new UserAdmin(userDTO);

            case "Manager":
                return new UserManeger(userDTO);

            case "Client":
                return new UserClient(userDTO);

            default:
            return new User(userDTO);

        }
    }


    @Deprecated
    public List<User> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                "http://localhost:8080/actor",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>(){});
        List<UserDTO> users = response.getBody();
        /*switch (users.getType()){

            case"Admin":
                return users.stream()
                        .map(UserAdmin::new)
                        .collect(Collectors.toList());

            break;
            case"Manager":
                return users.stream()
                        .map(UserManeger::new)
                        .collect(Collectors.toList());

            break;
            case"Client":
                return users.stream()
                        .map(UserClient::new)
                        .collect(Collectors.toList());

            break;
            default:
                return users.stream()
                        .map(User::new)
                        .collect(Collectors.toList());

        }*/
        return users.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }


    public void deleteUser(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/user/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }


    public void createActor(User user) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<UserDTO> body = new HttpEntity<>(
                user.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/user", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
    public List<User> findByName(String  name){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                "http://localhost:8080/user/"+name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>(){});
        List<UserDTO> users = response.getBody();

        return users.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }



}
